package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1520 {


    private static class Pos implements Comparable<Pos> {
        int m, n;

        public Pos(int m, int n) {
            this.m = m;
            this.n = n;
        }

        @Override
        public int compareTo(Pos o) {
            return map[o.m][o.n] - map[this.m][this.n];
        }
    }

    static int[] dm = {1, -1, 0, 0};
    static int[] dn = {0, 0, 1, -1};
    static int n, m;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        m = Integer.parseInt(stringTokenizer.nextToken());
        n = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[m][n];

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        PriorityQueue<Pos> queue = new PriorityQueue<>();
        queue.offer(new Pos(0, 0));

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        while (!queue.isEmpty()) {
            Pos current = queue.poll();
            int currentHeight = map[current.m][current.n];

            for (int d = 0; d < 4; d++) {
                int nextM = current.m + dm[d];
                int nextN = current.n + dn[d];

                if (!isSafe(nextM, nextN)) {
                    continue;
                }
                if (currentHeight <= map[nextM][nextN]) {
                    continue;
                }
                if (dp[nextM][nextN] == 0) {
                    queue.offer(new Pos(nextM, nextN));
                }
                dp[nextM][nextN] += dp[current.m][current.n];

            }
        }
        System.out.println(dp[m - 1][n - 1]);


    }



    private static boolean isSafe(int nextM, int nextN) {
        return 0 <= nextM && nextM < m && 0 <= nextN && nextN < n;
    }
}
