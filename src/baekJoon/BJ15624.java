package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ15624 {

    static int[] fibo;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        if (n == 1 || n == 2) {
            System.out.println(1);
            return;
        }
        if (n == 0) {
            System.out.println(0);
            return;
        }

        fibo = new int[n + 1];
        fibo[1] = 1;
        fibo[2] = 1;

        for (int i = 3; i <= n; i++) {
            fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 1_000_000_007;
        }

        System.out.println(fibo[n]);
    }

}
