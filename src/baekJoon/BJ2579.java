package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2579 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        int[] stairs = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(bufferedReader.readLine());
        }

        int[][] dp = new int[N + 1][2];

        dp[1][0] = 0;
        dp[1][1] = stairs[1];

        if (N >= 2) {
            dp[2][0] = dp[1][1];
            dp[2][1] = dp[1][1] + stairs[2];
        }

        for (int i = 3; i <= N; i++) {
            dp[i][0] = dp[i - 1][1];
            dp[i][1] = Math.max(dp[i - 3][1] + stairs[i - 1], dp[i - 2][1]) + stairs[i];
        }

        System.out.println(dp[N][1]);
    }
}
