package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14501 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {

            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            int time = Integer.parseInt(stringTokenizer.nextToken());
            int pay = Integer.parseInt(stringTokenizer.nextToken());

            if (i > 0) {
                dp[i] = Math.max(dp[i], dp[i - 1]); // 이전 날짜까지의 최대 수익을 현재 날짜에도 반영
//                System.out.println("dp[" + i + "] = " + dp[i]);
            }

            if (i + time <= n) {
                dp[i + time] = Math.max(dp[i + time], dp[i] + pay);
            }


        }

        dp[n] = Math.max(dp[n], dp[n - 1]); // 이전 날짜까지의 최대 수익을 현재 날짜에도 반영


        System.out.println(dp[n]);
    }
}
