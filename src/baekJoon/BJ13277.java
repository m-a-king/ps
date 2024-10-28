package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BJ13277 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        BigInteger a = new BigInteger(stringTokenizer.nextToken());
        BigInteger b = new BigInteger(stringTokenizer.nextToken());

        System.out.println(a.multiply(b));
    }
}
