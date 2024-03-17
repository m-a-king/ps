package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2468 {

    private static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int maxSafeArea = 1;


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());

        map = new int[n][n];
        int maxHeight = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] > maxHeight) {
                    maxHeight = map[i][j];
                }
            }
        }


        for (int i = 1; i <= maxHeight; i++) {
            bfs(i);
        }

        System.out.println(maxSafeArea);
    }

    private static void bfs(int rain) {
        Queue<Pos> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        int count = 0;

        while (true) {

            Pos startPos = findSearchablePos(rain, visited);

            if (startPos == null) {
                if (maxSafeArea < count) {
                    maxSafeArea = count;
                }
                break;
            } else {
                count++;
            }

            queue.offer(startPos);
            visited[startPos.x][startPos.y] = true;

            while (!queue.isEmpty()) {
                Pos current = queue.poll();

                for (int i = 0; i < 4; i++) {
                    Pos next = new Pos(current.x + dx[i], current.y + dy[i]);

                    if (isSafe(next, visited, rain)) {
                        visited[next.x][next.y] = true;
                        queue.offer(next);
                    }
                }
            }
        }

    }

    private static boolean isSafe(Pos pos, boolean[][] visited, int rain) {
        return 0 <= pos.x && pos.x < n && 0 <= pos.y && pos.y < n && !visited[pos.x][pos.y] && map[pos.x][pos.y] > rain;
    }

    private static Pos findSearchablePos(int rain, boolean[][] visited) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > rain && !visited[i][j]) {
                    return new Pos(i, j);
                }
            }
        }

        return null;
    }
}
