package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;

public class BJ7576 {

    static class Node {
        int row, col, date;

        public Node(int row, int col, int date) {
            this.row = row;
            this.col = col;
            this.date = date;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] mn = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int row = mn[1];
        int col = mn[0];

        int[][] tomatoBox = new int[row][col];
        boolean[][] visited = new boolean[row][col];
        Queue<Node> queue = new ArrayDeque<>();


        for (int i = 0; i < row; i++) {
            String[] input = bufferedReader.readLine().split(" ");
            for (int j = 0; j < col; j++) {
                int tomato = parseInt(input[j]);
                tomatoBox[i][j] = tomato;
                if (tomato == 1) {
                    queue.offer(new Node(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        int maxDate = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};
            int nextDate = current.date + 1;

            for (int i = 0; i < 4; i++) {
                int nextCol = current.col + dx[i];
                int nextRow = current.row + dy[i];

                if (isSafe(nextCol, nextRow, row, col, visited, tomatoBox)) {
                    tomatoBox[nextRow][nextCol] = 1;
                    visited[nextRow][nextCol] = true;
                    queue.offer(new Node(nextRow, nextCol, nextDate));
                    maxDate = max(nextDate, maxDate);
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (tomatoBox[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(maxDate);


    }

    private static boolean isSafe(int nextCol, int nextRow, int row, int col, boolean[][] visited, int[][] tomatoBox) {
        return nextCol >= 0 && nextCol < col && nextRow >= 0 && nextRow < row && !visited[nextRow][nextCol] && tomatoBox[nextRow][nextCol] == 0;
    }
}
