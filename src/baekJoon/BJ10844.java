package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ10844 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        long[][] dp = new long[n + 1][10];
        long mod = 1000000000;

        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][1]; // dp[i+1][0+1]
                } else if (j == 9) {
                    dp[i][j] = dp[i - 1][8]; // dp[i-1][8+1]
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
                }
            }
        }

        long res = 0;

        for (int i = 0; i <= 9; i++) {
            res = (res + dp[n][i]) % mod;
        }

        System.out.println(res);

    }
}

