package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2636 {
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int rows = Integer.parseInt(stringTokenizer.nextToken());
        int cols = Integer.parseInt(stringTokenizer.nextToken());

        int[][] map = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 0; j < cols; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int time = 0;
        int lastCheese = 0;

        while (true) {
            boolean[][] visited = new boolean[rows][cols];
            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{0, 0}); // 테두리는 다 비어있어서 가능
            visited[0][0] = true;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && !visited[nx][ny]) {
                        visited[nx][ny] = true; // 공기 묻히기
                        if (map[nx][ny] == 0) queue.offer(new int[]{nx, ny}); // 공기에 공기 묻히면 공기는 더 퍼진다
                    }
                }
            }

            int cheeseCount = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (map[i][j] == 1 && visited[i][j]) {
                        map[i][j] = 0;
                        cheeseCount++;
                    }
                }
            }

            if (cheeseCount == 0) {
                break;
            }

            lastCheese = cheeseCount;
            time++;
        }

        System.out.println(time);
        System.out.println(lastCheese);
    }
}