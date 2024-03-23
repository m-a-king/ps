package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ7562 {

    private static class Pos {
        int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;

        }
    }

    static int n;
    static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

    static boolean[][] visited;


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        for (int t = 0; t < tc; t++) {
            n = Integer.parseInt(bufferedReader.readLine());

            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            Pos start = new Pos(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));

            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            Pos target = new Pos(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));

            if (start.row == target.row && start.col == target.col) {
                stringBuilder.append(0).append("\n");
                continue; // 시작점과 목표점이 같으면 더 이상의 탐색 없이 0 출력
            }

            Queue<Pos> queue = new ArrayDeque<>();
            visited = new boolean[n][n];

            queue.offer(start);
            visited[start.row][start.col] = true;
            int[][] distance = new int[n][n];
            distance[start.row][start.col] = 0;


            while (!queue.isEmpty()) {
                Pos current = queue.poll();

                if (current.row == target.row && current.col == target.col) {
                    stringBuilder.append(distance[current.row][current.col]).append("\n");
                    break;
                }

                for (int i = 0; i < 8; i++) {
                    int nextRow = current.row + dx[i];
                    int nextCol = current.col + dy[i];

                    if (isSafe(nextRow, nextCol)) {
                        visited[nextRow][nextCol] = true;

                        queue.offer(new Pos(nextRow, nextCol));
                        distance[nextRow][nextCol] = distance[current.row][current.col] + 1;
                    }
                }
            }
        }

        System.out.println(stringBuilder);


    }

    private static boolean isSafe(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < n && 0 <= nextCol && nextCol < n && !visited[nextRow][nextCol];
    }
}
