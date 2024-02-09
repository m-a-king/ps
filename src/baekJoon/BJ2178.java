package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BJ2178 {

    private static class Pos {
        int x, y, cost;

        public Pos(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(bufferedReader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        Queue<Pos> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        queue.offer(new Pos(0, 0, 1));
        visited[0][0] = true;

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

                if (isSafe(nextX, nextY, n, m, visited, map)) {
                    visited[nextX][nextY] = true;
                    queue.offer(new Pos(nextX, nextY, current.cost + 1));
                }

            }
        }
    }

    private static boolean isSafe(int x, int y, int n, int m, boolean[][] visited, int[][] map) {
        return 0 <= x && x < n && 0 <= y && y < m && !visited[x][y] && map[x][y] == 1;
    }
}
