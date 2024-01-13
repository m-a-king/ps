package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BJ7569 {

    static int[][][] tomato;
    static boolean[][][] visited;

    static class Position {

        int col, row, height, day;

        Position(int col, int row, int height, int day) {
            this.col = col;
            this.row = row;
            this.height = height;
            this.day = day;
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int col = input[0];
        int row = input[1];
        int height = input[2];

        tomato = new int[height][row][col];
        visited = new boolean[height][row][col];


        for (int i = 0; i < height; i++) {
            for (int j = 0; j < row; j++) {
                int[] input2 = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int k = 0; k < col; k++) {
                    tomato[i][j][k] = input2[k];
                }
            }
        }



        Queue<Position> queue = new ArrayDeque<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    if (tomato[i][j][k] == 1) {
                        queue.offer(new Position(k, j, i, 0));
                    }
                }
            }
        }
        int totalDay = 0;

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            int[] dx = {0, 0, 0, 0, 1, -1};
            int[] dy = {0, 0, 1, -1, 0, 0};
            int[] dz = {1, -1, 0, 0, 0, 0};

            int nDay = current.day + 1;

            for (int d = 0; d < 6; d++) {
                int nx = current.col + dx[d];
                int ny = current.row + dy[d];
                int nz = current.height + dz[d];

                boolean sideCheck = nx >= 0 && nx < col && ny >= 0 && ny < row && nz >= 0 && nz < height;

                if (sideCheck && tomato[nz][ny][nx] == 0 && !visited[nz][ny][nx]) {
                    totalDay = nDay;
                    tomato[nz][ny][nx] = 1;
                    visited[nz][ny][nx] = true;
                    queue.offer(new Position(nx, ny, nz, nDay));
                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    if (tomato[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(totalDay);
    }
}
