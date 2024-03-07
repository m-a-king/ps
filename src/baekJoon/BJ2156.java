package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2156 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        int[][] dp = new int[n + 1][3];
        // dp[x][0] -> x번째 차례에 마시지 않겠다.
        // dp[x][1] -> x번째 차례에 마시지만 직전에는 마시지 않겠다.
        // dp[x][2] -> x번째 차례에 마시고, 직전에도 마셨다.
        int podoju;

        podoju= Integer.parseInt(bufferedReader.readLine());
        dp[1][0] = 0;
        dp[1][1] = podoju;
        dp[1][2] = 0;

        for (int i = 2; i <= n; i++) {
            podoju = Integer.parseInt(bufferedReader.readLine());
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 2][2]) + podoju;
            dp[i][2] = dp[i - 1][1] + podoju;
        }

        System.out.println(Math.max(dp[n][2], Math.max(dp[n][0], dp[n][1])));
    }
}
