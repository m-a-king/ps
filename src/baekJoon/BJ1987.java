package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1987 {

    static int r, c;
    static int[][] map;
    static boolean[] visited;
    static int res = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] rc = bufferedReader.readLine().split(" ");
        r = Integer.parseInt(rc[0]);
        c = Integer.parseInt(rc[1]);

        map = new int[r + 1][c + 1];
        visited = new boolean[26];

        for (int i = 1; i <= r; i++) {
            String input = bufferedReader.readLine();
            for (int j = 1; j <= c; j++) {
                map[i][j] = input.charAt(j - 1) - 'A';
            }
        }

        visited[map[1][1]] = true;
        dfs(1, 1, 1);

        System.out.println(res);

    }

    private static void dfs(int x, int y, int cost) {
        res = Math.max(res, cost);

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (0 < nextX && nextX <= r && 0 < nextY && nextY <= c) {
                if (!visited[map[nextX][nextY]]) {
                    visited[map[nextX][nextY]] = true;
                    dfs(nextX, nextY, cost + 1);
                    visited[map[nextX][nextY]] = false;
                }
            }


        }
    }
}
