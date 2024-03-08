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
        // dp[x][1] -> x번째 차례에 마시지만 직전에는 마시지 않았다.
        // dp[x][2] -> x번째 차례에 마시고, 직전에도 마셨다.

        int podoju; // 포도주의 양

        // 아래 반복문에서 인덱스를 벗어나는 것을 피하기 위한 dp[1]을 직접 선언
        podoju= Integer.parseInt(bufferedReader.readLine());
        dp[1][0] = 0; // 선언하지 않아도 무방
        dp[1][1] = podoju; // 선언해야함
        dp[1][2] = 0; // 선언하지 않아도 무방

        for (int i = 2; i <= n; i++) {
            podoju = Integer.parseInt(bufferedReader.readLine());

            // MAX(직전에 마시지 않은 경우, 직전에 마신 경우, 전전과 직전에 마신 경우) 
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));

            // 직전에 마시지 않은 경우 + 이번에 마시기
            dp[i][1] = dp[i - 1][0] + podoju;

            //    직전에 마신 경우   + 이번에 마시기
            dp[i][2] = dp[i - 1][1] + podoju;
        }

        // 마지막 단계에서의 최대값 출력
        System.out.println(Math.max(dp[n][2], Math.max(dp[n][0], dp[n][1])));
    }
}