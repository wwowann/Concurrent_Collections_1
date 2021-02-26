import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final int quentityBell = 10;
        Queue<String> phoneNumers = new ConcurrentLinkedQueue<>();
        Atc atc = new Atc();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < quentityBell; i++) {
                phoneNumers.add(atc.numberPhone());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread operator1 = new Thread(null, () -> metod(phoneNumers));
        Thread operator2 = new Thread(null, () -> metod(phoneNumers));
        Thread operator3 = new Thread(null, () -> metod(phoneNumers));
        thread.start();
        operator1.start();
        operator2.start();
        operator3.start();
        thread.join();

    }

    public static void metod(Queue<String> phoneNumers) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (phoneNumers.peek() != null) {
            System.out.println("Оператор " + Thread.currentThread().getName() +
                    " обрабатывает звонок по номеру " + phoneNumers.peek());
            phoneNumers.poll();
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(5) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " Звонков в очереди нет");

    }
}
