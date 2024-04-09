package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1520 {

    private static class Pos implements Comparable<Pos> {
        int m, n;

        public Pos(int m, int n) {
            this.m = m;
            this.n = n;
        }

        // 높이를 기준으로 내림차순으로 정렬
        @Override
        public int compareTo(Pos o) {
            return map[o.m][o.n] - map[this.m][this.n];
        }
    }

    static int[] dm = {1, -1, 0, 0};
    static int[] dn = {0, 0, 1, -1};
    static int m, n;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        // 세로 m, 가로 n 입력
        m = Integer.parseInt(stringTokenizer.nextToken());
        n = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[m][n];

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        // 우선순위 큐 사용 (내림차순, 최대 힙)
        PriorityQueue<Pos> queue = new PriorityQueue<>();
        queue.offer(new Pos(0, 0));

        // 해당 지점까지 이동할 수 있는 경로의 수를 저장
        // visited 배열의 역할도 가짐
        int[][] dp = new int[m][n];
        // 시작 지점으로 가는 방법은 처음 시작할 때, 한 번
        dp[0][0] = 1;

        while (!queue.isEmpty()) {
            Pos current = queue.poll();
            int currentHeight = map[current.m][current.n];

            for (int d = 0; d < 4; d++) {
                int nextM = current.m + dm[d];
                int nextN = current.n + dn[d];

                // 배열 범위 검사
                if (!isSafe(nextM, nextN)) {
                    continue;
                }
                // 다음 지점으로 이동 가능한지 확인 (높이 체크)
                if (currentHeight <= map[nextM][nextN]) {
                    continue;
                }
                // 해당 지점에 처음 왔다면?
                if (dp[nextM][nextN] == 0) {
                    // 큐에 삽입하고 아래 식으로
                    queue.offer(new Pos(nextM, nextN));
                }
                // 해당 지점으로 올 수 있는 경로의 수 = (1) + (2)
                // (1) 이전에 계산된 해당 지점으로 올 수 있는 경로의 수
                // (2) 해당 지점으로 올 수 있는 이전 지점으로 올 수 있는 경로의 수
                dp[nextM][nextN] += dp[current.m][current.n];

            }
        }

        // 결과 출력
        System.out.println(dp[m - 1][n - 1]);
    }


    private static boolean isSafe(int nextM, int nextN) {
        return 0 <= nextM && nextM < m && 0 <= nextN && nextN < n;
    }
}
