package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1987 {

    static int r, c;
    static int[][] map;
    static boolean[] visited = new boolean[26];
    static int res = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] rc = bufferedReader.readLine().split(" ");
        r = Integer.parseInt(rc[0]);
        c = Integer.parseInt(rc[1]);

        map = new int[r + 1][c + 1];

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


        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (0 < nextX && nextX <= r && 0 < nextY && nextY <= c) {
                int next = map[nextX][nextY];
                if (!visited[next]) {
                    visited[next] = true;
                    dfs(nextX, nextY, cost + 1);
                    visited[next] = false;
                }
            }


        }
    }
}
