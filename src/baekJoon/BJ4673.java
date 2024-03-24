package baekJoon;

import java.io.IOException;

public class BJ4673 {

    public static void main(String[] args) throws IOException {

        boolean[] numbers = new boolean[10001];

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i <= 10000; i++) {
            int sumByI = checkNumber(i);
            numbers[sumByI] = true;
        }

        for (int i = 1; i <= 10000; i++) {
            if (!numbers[i]) {
                stringBuilder.append(i).append("\n");
            }
        }

        System.out.println(stringBuilder);
    }

    private static int checkNumber(int number) {
        int sum = number;
        while (number != 0) {

            sum += number % 10;
            number /= 10;
        }

        return sum > 10000 ? 0 : sum;
    }
}
