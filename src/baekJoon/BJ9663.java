package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ9663 {

    static int solution = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(bufferedReader.readLine());

        int[] board = new int[N];
        nQueens(0, board);

        System.out.println(solution);

    }

    public static void nQueens(int row, int[] board) {
        int N = board.length;

        if (N == row) {
            solution++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (safeCheck(row, col, board)) {
                board[row] = col;
                nQueens(row+1, board);
            }
        }


    }

    public static boolean safeCheck(int row, int col, int[] board) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(row - i) == Math.abs(col - board[i])) {
                return false;
            }
        }

        return true;
    }
}
