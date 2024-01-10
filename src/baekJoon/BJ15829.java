package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

import static java.lang.Integer.parseInt;


public class BJ15829 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numCount = parseInt(bufferedReader.readLine());

        String word = bufferedReader.readLine();

        System.out.println(hashFunction(word));

    }

    private static BigInteger hashFunction(String word) {
        BigInteger hashValue = BigInteger.ZERO;
        BigInteger base = BigInteger.valueOf(31);
        BigInteger modules = new BigInteger("1234567891");

        for (int i = 0; i < word.length(); i++) {
            int charValue = word.charAt(i) - 'a' + 1;

            BigInteger exponent = base.pow(i);

            hashValue = hashValue.add(BigInteger.valueOf(charValue).multiply(exponent)).mod(modules);
        }

        return hashValue;
    }
}
