import java.util.Random;

public class Atc {
    public String numberPhone() {
        Random random = new Random();
        StringBuilder phone = new StringBuilder();
        phone.append("+7 9");
        for (int i = 0; i < 9; i++) {
            if (i == 2 || i == 5 || i == 7) {
                phone.append("-");
            }
            Integer number = random.nextInt(9);
            phone.append(number);
        }
        return phone.toString();
    }
}
