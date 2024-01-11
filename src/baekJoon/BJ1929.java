package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ1929 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        int start = parseInt(input[0]);
        int end = parseInt(input[1]);

        for (int i = start; i <= end; i++) {

            if (checkPrime(i)) {
                System.out.println(i);
            }
        }
    }

    private static boolean checkPrime(int target) {
        if (target < 2) {
            return false;
        }
        for (int i = 2; i * i <= target; i++) {
            if (target % i == 0) {
                return false;
            }
        }

        return true;
    }
}
