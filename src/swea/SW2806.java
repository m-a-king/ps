package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class SW2806 {
    static int[] solution;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int repeat = parseInt(bufferedReader.readLine());
        solution = new int[repeat];

        for (int i = 0; i < repeat; i++) {

            int n = parseInt(bufferedReader.readLine());

            int[] board = new int[n];

            nQueens(board, 0, i);

            System.out.println("#" + (i+1) + " " + solution[i]);

        }
    }

    public static void nQueens(int[] board, int row, int repeat) {
        int n = board.length;

        if (row == n) {
            solution[repeat]++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col;
                nQueens(board, row + 1, repeat);

            }
        }


    }

    private static boolean isSafe(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(row - i) == Math.abs(col - board[i])) {
                return false;
            }
        }

        return true;
    }
}
