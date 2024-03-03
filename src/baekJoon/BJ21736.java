package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ21736 {

    private static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        String[][] map = new String[n][m];
        boolean check = false;
        Queue<Pos> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = bufferedReader.readLine().split("");
            if (!check) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j].equals("I")) {
                        queue.offer(new Pos(i, j));
                        check = true;
                        visited[i][j] = true;
                    }
                }
            }
        }

        int count = 0;

        while (!queue.isEmpty()) {
            Pos current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];

                if (isSafe(nextX, nextY) && !visited[nextX][nextY] && !map[nextX][nextY].equals("X")) {
                    visited[nextX][nextY] = true;
                    queue.offer(new Pos(nextX, nextY));
                    if (map[nextX][nextY].equals("P")) {
                        count++;
                    }
                }
            }
        }

        if (count == 0) {
            System.out.println("TT");
        } else {
            System.out.println(count);
        }


    }

    private static boolean isSafe(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}
