package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;

public class BJ14500 {

    static int[][] board;
    static int row;
    static int col;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        row = parseInt(input[0]);
        col = parseInt(input[1]);

        board = new int[row][col];

        for (int i = 0; i < row; i++) {
            String[] line = bufferedReader.readLine().split(" ");
            for (int j = 0; j < col; j++) {
                board[i][j] = parseInt(line[j]);
            }
        }

        int maxTotal = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                maxTotal = max(maxTotal, dfs(i, j, 3, -1));
            }
        }

        System.out.println(maxTotal);

    }

    private static int dfs(int row, int col, int count, int preD) {
        int now = board[row][col];
        int specialCase = 0;

        if (count == 0) {
            return now;
        } else if (count == 2) {
            specialCase = calcSpecialCase(row, col, preD, now);
        }
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        if (preD == 0) {
            preD = 1;
        } else if (preD == 1) {
            preD = 0;
        } else if (preD == 2) {
            preD = 3;
        } else if (preD == 3) {
            preD = 2;
        }

        int res = 0;

        for (int i = 0; i < 4; i++) {
            if (i == preD) {
                continue;
            }

            int nextRow = row + dy[i];
            int nextCol = col + dx[i];

            if (isSafe(nextRow, nextCol)) {
                res = max(res, now + dfs(nextRow, nextCol, count - 1, i));
                res = max(res, specialCase);
            }

        }
        return res;
    }

    private static int calcSpecialCase(int row, int col, int preD, int now) {
        if (preD == 0 || preD == 1) { // 상하로 움직였다면 -> 좌우로 이동
            if (isSafe(row, col - 1) && isSafe(row, col + 1)) {
                int left = board[row][col - 1];
                int right = board[row][col + 1];
                int res = left + right;
                return now + res;
            } else {
                return 0;
            }
        } else if (preD == 2 || preD == 3) { // 좌우로 움직였다면 -> 상하로 이동
            if (isSafe(row - 1, col) && isSafe(row + 1, col)) {
                int up = board[row - 1][col];
                int down = board[row + 1][col];
                int res = up + down;
                return now + res;
            } else {
                return 0;
            }
        }
        return 0;
    }

    private static boolean isSafe(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < row && 0 <= nextCol && nextCol < col;
    }

}
