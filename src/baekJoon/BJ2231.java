package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.lang.Math.min;

public class BJ2231 {

    static String input;
    static int length;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        input = bufferedReader.readLine();
        int number = parseInt(input);
        length = input.split("").length;

        int res = solution(number, length * 10);

        System.out.println(res);

    }

    private static int solution(int target, int repeat) {
        int expect = target - repeat;
        int minExpect = MAX_VALUE;
        int count = 0;

        for (int i = 0; i < repeat; i++) {
            expect++;

            int res = expect + calcEachDigit(expect);

            if (res == target) {
                minExpect = min(minExpect, expect);
                count++;
            }

        }
        if (count == 0) {
            return 0;
        }
        return minExpect;
    }

    private static int calcEachDigit(int number) {

        int sum = 0;

        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }

        return sum;
    }


}
