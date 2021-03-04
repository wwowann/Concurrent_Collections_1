import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final int quentityBell = 20;
        final int queueSize = 10;
        BlockingQueue<String> phoneNumers = new ArrayBlockingQueue<>(queueSize);
        Atc atc = new Atc();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < quentityBell; i++) {
                try {
                    phoneNumers.put(atc.numberPhone());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread operator1 = new Thread(null, () -> metod(phoneNumers, thread));
        Thread operator2 = new Thread(null, () -> metod(phoneNumers, thread));
        Thread operator3 = new Thread(null, () -> metod(phoneNumers, thread));
        thread.start();
        operator1.start();
        operator2.start();
        operator3.start();
        thread.join();
    }

    public static void metod(BlockingQueue<String> phoneNumers, Thread thread) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            while (thread.isAlive() || !phoneNumers.isEmpty()) {
                System.out.println("Оператор " + Thread.currentThread().getName() +
                        " обрабатывает звонок по номеру " + phoneNumers.poll());
                Random random = new Random();
                try {
                    Thread.sleep(random.nextInt(5) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + " Звонков в очереди нет");
    }
}
