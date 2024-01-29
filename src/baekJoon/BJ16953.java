package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ16953 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        int start = parseInt(input[0]);
        int target = parseInt(input[1]);
        int count = 1;

        while (true) {

            if (target == start) {
                break;
            }
            if (target < start) {
                System.out.println("-1");
                return;
            }
            count++;
            int temp = 0;
            String[] split = String.valueOf(target).split("");
            int length = split.length;
            int lastNumber = parseInt(split[length - 1]);
            if (lastNumber == 1) {
                temp = target / 10;

            } else if (target % 2 == 0) {
                temp = target / 2;
            } else {
                System.out.println("-1");
                return;
            }

            target = temp;
        }

        System.out.println(count);

    }
}
