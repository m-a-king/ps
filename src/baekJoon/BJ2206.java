package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BJ2206 {

    private static class Pos {
        int x, y, cost;
        boolean check;

        public Pos(int x, int y, int cost, boolean check) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.check = check;
        }
    }

    static int n;
    static int m;
    static int[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");

        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        map = new int[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            String line = bufferedReader.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        Queue<Pos> queue = new ArrayDeque<>();

        queue.offer(new Pos(0, 0, 1, false));

        while (!queue.isEmpty()) {
            Pos current = queue.poll();

            if (current.x == n - 1 && current.y == m - 1) {
                System.out.println(current.cost);

                return;
            }

            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];
                int nextCheck = current.check ? 1 : 0;

                if (isSafe(nextX, nextY, nextCheck)) {

                    if (map[nextX][nextY] == 0) {
                        visited[nextX][nextY][nextCheck] = true;
                        queue.offer(new Pos(nextX, nextY, current.cost + 1, current.check));
                    }
                    if (map[nextX][nextY] == 1 && !current.check) {
                        visited[nextX][nextY][nextCheck] = true;
                        queue.offer(new Pos(nextX, nextY, current.cost + 1, true));
                    }
                }
            }
        }

        System.out.println(-1);
    }

    private static boolean isSafe(int nextX, int nextY, int nextCheck) {
        return 0 <= nextX && nextX < n && 0 <= nextY && nextY < m && !visited[nextX][nextY][nextCheck];
    }
}
