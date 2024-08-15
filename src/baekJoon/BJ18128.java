package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ18128 {

    private static class Pos implements Comparable<Pos> {
        int row, col, weight;

        public Pos(int row, int col, int weight) {
            this.row = row;
            this.col = col;
            this.weight = weight;
        }


        @Override
        public int compareTo(Pos o) {
            return this.weight - o.weight;
        }
    }

    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    static int n, w;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken()) + 1;
        w = Integer.parseInt(stringTokenizer.nextToken());

        boolean[][] isRock = new boolean[n][n];

        int[][] weightMap = new int[n][n];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                weightMap[i][j] = 9;
            }
        }

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < w; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            pq.offer(new Pos(x, y, 0));
            visited[x][y] = true;
            weightMap[x][y] = 0;
        }

        for (int i = 1; i < n; i++) {
            String input = bufferedReader.readLine();
            for (int j = 1; j < n; j++) {
                isRock[i][j] = input.charAt(j - 1) == '1';
            }
        }


        while (!pq.isEmpty()) {

            Pos curr = pq.poll();

            for (int d = 0; d < 4; d++) {
                int nextX = curr.row + dx[d];
                int nextY = curr.col + dy[d];

                if (!isSafe(nextX, nextY)) continue;
                if (visited[nextX][nextY]) continue;
                visited[nextX][nextY] = true;

                pq.offer(new Pos(nextX, nextY, curr.weight + 1));
                weightMap[nextX][nextY] = curr.weight + 1;
            }
        }

        weightMap[1][1] = 0;
        weightMap[n - 1][n - 1] = 0;

        visited = new boolean[n][n];
        pq.offer(new Pos(1, 1, 0));
        visited[1][1] = true;

        while (!pq.isEmpty()) {
            Pos curr = pq.poll();

            for (int d = 0; d < 8; d++) {
                int nextX = curr.row + dx[d];
                int nextY = curr.col + dy[d];

                if (!isSafe(nextX, nextY)) continue;
                if (!isRock[nextX][nextY]) continue;
                if (visited[nextX][nextY]) continue;
                visited[nextX][nextY] = true;

                if (nextX == n - 1 && nextY == n - 1) {
                    System.out.println(curr.weight);
                    return;
                }

                int nextWeight = Math.max(curr.weight, weightMap[nextX][nextY]);

                pq.offer(new Pos(nextX, nextY, nextWeight));
            }
        }

        System.out.println(-1);

    }

    private static boolean isSafe(int x, int y) {
        return 1 <= x && x < n && 1 <= y && y < n;
    }
}
