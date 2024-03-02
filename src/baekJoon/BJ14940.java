package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class BJ14940 {
    static int n;
    static int m;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

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
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        int[][] map = new int[n][m];
        Pos target = null;

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] == 2) {
                    target = new Pos(i, j, 0);
                }
            }
        }

        Queue<Pos> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        queue.offer(target);
        visited[target.x][target.y] = true;
        map[target.x][target.y] = 0;

        while (!queue.isEmpty()) {
            Pos current = queue.poll();


            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];

                if (isSafe(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] == 1) {
                    visited[nextX][nextY] = true;
                    queue.offer(new Pos(nextX, nextY, current.cost + 1));
                    map[nextX][nextY] = current.cost + 1;
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    stringBuilder.append(-1).append(" ");
                } else {
                    stringBuilder.append(map[i][j]).append(" ");
                }
            }
            stringBuilder.append("\n");

        }

        System.out.println(stringBuilder);
    }

    private static boolean isSafe(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}
