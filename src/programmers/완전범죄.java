package programmers;

import java.util.Arrays;

public class 완전범죄 {

    public static final int INF = 1000000;

    public static void main(String[] args) {
        Solution solution = new Solution();

        final int[][] info = {{1, 2}, {2, 3}, {2, 1}};
        int n = 4;
        int m = 4;
        int result = 2;

        System.out.println("answer =" + solution.solution(info, n, m));
    }

    static class Solution {

        public int solution(int[][] info, int n, int m) {
            int answer = INF;

            int[][] dp = new int[info.length + 1][m];
            for (int i = 0; i <= info.length; i++) {
                Arrays.fill(dp[i], INF);
            }
            dp[0][0] = 0;

            // i -> 물건 훔친 개수
            // j -> B 도둑 증거 개수
            for (int i = 1; i <= info.length; i++) {
                int a = info[i - 1][0];
                int b = info[i - 1][1];

                for (int j = 0; j < m; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);

                    if (j + b < m) {
                        dp[i][j + b] = Math.min(dp[i][j + b], dp[i - 1][j]);
                    }
                }
            }

            System.out.println();
            for (int x = 0; x < dp.length; x++) {
                for (int y = 0; y < dp[x].length; y++) {
                    System.out.print(dp[x][y] == INF ? "X " : dp[x][y] + " ");
                }
                System.out.println();
            }

            for (int i = 0; i < m; i++) {
                answer = Math.min(answer, dp[info.length][i]);
                System.out.println(answer);
            }

            return answer>= n ? -1 :answer;
        }
    }
}
