package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;

public class BJ9465 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tc = parseInt(bufferedReader.readLine());

        for (int testCase = 0; testCase < tc; testCase++) {

            int n = parseInt(bufferedReader.readLine());

            int[][] stickers = new int[2][n];
            stickers[0] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            stickers[1] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[][] dp = new int[3][n];

            dp[0][0] = stickers[0][0];
            dp[1][0] = stickers[1][0];
            dp[2][0] = 0;

            for (int i = 1; i < n; i++) {
                dp[0][i] = max(dp[2][i - 1], dp[1][i - 1]) + stickers[0][i];
                dp[1][i] = max(dp[2][i - 1], dp[0][i - 1]) + stickers[1][i];
                dp[2][i] = max(max(dp[1][i - 1], dp[0][i - 1]), dp[2][i - 1]);
            }

            int max = max(max(dp[0][n - 1], dp[1][n - 1]), dp[2][n - 1]);

            System.out.println(max);
        }

    }
}
