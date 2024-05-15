package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ15730 {

    static int[][] map;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int result = calculateWater(map, n, m);
        System.out.println(result); // 결과 출력
    }

    private static int calculateWater(int[][] map, int n, int m) {
        PriorityQueue<Cell> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][m];

        // 테두리 채우기
        for (int i = 0; i < n; i++) {
            pq.offer(new Cell(i, 0, map[i][0]));
            pq.offer(new Cell(i, m - 1, map[i][m - 1]));
            visited[i][0] = true;
            visited[i][m - 1] = true;
        }
        for (int j = 1; j < m - 1; j++) {
            pq.offer(new Cell(0, j, map[0][j]));
            pq.offer(new Cell(n - 1, j, map[n - 1][j]));
            visited[0][j] = true;
            visited[n - 1][j] = true;
        }

        int totalWater = 0;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            Cell cell = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cell.x + dx[i];
                int ny = cell.y + dy[i];

                if (isSafe(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    totalWater += Math.max(0, cell.height - map[nx][ny]);
                    pq.offer(new Cell(nx, ny, Math.max(cell.height, map[nx][ny])));
                }
            }
        }

        return totalWater;
    }

    private static boolean isSafe(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    static class Cell implements Comparable<Cell> {
        int x, y, height;

        Cell(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        @Override
        public int compareTo(Cell other) {
            return this.height - other.height;
        }
    }
}
