package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

import static java.lang.Integer.parseInt;

public class BJ1676 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int number = parseInt(bufferedReader.readLine());
        int count = 0;

        BigInteger res = BigInteger.ONE;

        for (int i = 2; i <= number; i++) {

            res = res.multiply(BigInteger.valueOf(i));
        }

        String[] splitNumber = res.toString().split("");

        for (int i = splitNumber.length - 1; i >= 0; i--) {
            if (splitNumber[i].equals("0")) {
                count++;
            } else {
                break;
            }
        }
        System.out.println(count);
    }
}
