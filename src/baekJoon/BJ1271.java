package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BJ1271 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        // Using BigInteger to handle very large integers
        BigInteger money = new BigInteger(stringTokenizer.nextToken());
        BigInteger per = new BigInteger(stringTokenizer.nextToken());

        // Calculate quotient and remainder
        BigInteger quotient = money.divide(per);
        BigInteger remainder = money.remainder(per);

        // Print results
        System.out.println(quotient);
        System.out.println(remainder);
    }
}