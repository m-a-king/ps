package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ4963 {

    // 상, 하, 좌, 우, 상좌, 상우, 하좌, 하우
    static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    static int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        while (true) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            int w = Integer.parseInt(stringTokenizer.nextToken());
            int h = Integer.parseInt(stringTokenizer.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            int[][] map = new int[h][w];
            boolean[][] visited = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            int count = 0;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!visited[i][j] && map[i][j] == 1) {
                        dfs(i, j, map, visited, h, w);
                        count++;
                    }
                }
            }

            stringBuilder.append(count).append("\n");
        }

        System.out.println(stringBuilder);

    }

    private static void dfs(int x, int y, int[][] map, boolean[][] visited, int h, int w) {

        if (!isSafe(x, y, h, w, map, visited)) {
            return;
        }
        visited[x][y] = true; // 현재 위치를 방문 처리

        for (int i = 0; i < 8; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            dfs(nextX, nextY, map, visited, h, w);
        }
    }

    private static boolean isSafe(int x, int y, int h, int w, int[][] map, boolean[][] visited) {
        return 0 <= x && x < h && 0 <= y && y < w && !visited[x][y] && map[x][y] == 1;
    }
}
