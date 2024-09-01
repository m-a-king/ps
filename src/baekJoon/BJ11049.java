package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11049 {

    private static class Matrix {
        int row, col;

        public Matrix(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        Matrix[] matrices = new Matrix[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int r = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());

            matrices[i] = (new Matrix(r, c));
        }

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }


        for (int k = 1; k < n; k++) { // 길이 정하고

            for (int i = 1; i + k <= n; i++) { // 시작 지점
                int j = i + k; // 끝 지점

                for (int s = i; s < j; s++) { // 시작 지점과 끝 지점을 나누는 지점(시작부터 끝-1까지)

                    int curr = dp[i][s] + dp[s + 1][j] + matrices[i].row * matrices[s].col * matrices[j].col;
                    dp[i][j] = Math.min(dp[i][j], curr);
                }
            }
        }

        System.out.println(dp[1][n]);

    }

}
