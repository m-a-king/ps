package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2748 {

    static long[] fibo;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());

        fibo = new long[n + 2]; // fibo[2]의 초기화를 위해서
        fibo[0] = 0;
        fibo[1] = 1;
        fibo[2] = 1;

        System.out.println(fiboCalc(n));

    }

    private static long fiboCalc(int n) {
        if (n == 0) {
            return n;
        }
        if (fibo[n] > 0) {
            return fibo[n];
        }
        if (fibo[n] == 0) {
            fibo[n] = fiboCalc(n - 1) + fiboCalc(n - 2);
            return fibo[n];
        }
        return 0;
    }
}
