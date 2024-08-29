package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10942 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        for (int i = 1; i < n; i++) {
            dp[i+1][i] = -1;
        }

        for (int c = 1; c <= n; c++) {
            for (int r = 1; r < c; r++) {
                if (dp[c][c] == dp[r][r]) {
                    if (dp[r + 1][c - 1] == 0) continue;
                    dp[r][c] = 1;
                }
            }
        }

//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        int m = Integer.parseInt(bufferedReader.readLine());

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            int s = Integer.parseInt(stringTokenizer.nextToken());
            int e = Integer.parseInt(stringTokenizer.nextToken());

            stringBuilder.append(dp[s][e] == 0 ? 0 : 1).append("\n");
        }

        System.out.print(stringBuilder);


    }
}
