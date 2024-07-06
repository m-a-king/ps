package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12100 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n;
    static int maxValue = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());

        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        simulate(board, 5);

        System.out.println(maxValue);

    }

    private static void simulate(int[][] board, int count) {
        if (count == 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    maxValue = Math.max(maxValue, board[i][j]);
                }
            }

            return;
        }

        for (int d = 0; d < 4; d++) {
            int[][] newBoard = copyBoard(board);
            newBoard = move(d, newBoard);
            simulate(newBoard, count - 1);
        }
    }

    private static int[][] move(int d, int[][] board) {
//        System.out.println("d = " + d);
//        System.out.println("d0 d1 d2 d3");
//        System.out.println("좌  우  상  하");
//        System.out.println();

        board = shift(d, board);
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        board = merge(d, board);
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        board = shift(d, board);
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        System.out.println();
//        System.out.println();

        return board;
    }

    private static int[][] shift(int d, int[][] board) {

        if (d == 0) { // 좌로 밀착
            for (int i = 0; i < n; i++) {
                int[] temp = new int[n];
                int idx = 0;

                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 0) continue;
                    temp[idx++] = board[i][j];
                }
                board[i] = temp;
            }
        }

        if (d == 1) { // 우로 밀착
            for (int i = 0; i < n; i++) {
                int[] temp = new int[n];
                int idx = n - 1;

                for (int j = n - 1; j >= 0; j--) {
                    if (board[i][j] == 0) continue;
                    temp[idx--] = board[i][j];
                }
                board[i] = temp;
            }
        }
        if (d == 2) { // 위로 밀착
            for (int j = 0; j < n; j++) {
                int[] temp = new int[n];
                int idx = 0;

                for (int i = 0; i < n; i++) {
                    if (board[i][j] == 0) continue;
                    temp[idx++] = board[i][j];
                }
                for (int i = 0; i < n; i++) {
                    board[i][j] = temp[i];
                }
            }
        }
        if (d == 3) { // 아래로 밀착
            for (int j = 0; j < n; j++) {
                int[] temp = new int[n];
                int idx = n - 1;

                for (int i = n - 1; i >= 0; i--) {
                    if (board[i][j] == 0) continue;
                    temp[idx--] = board[i][j];
                }
                for (int i = 0; i < n; i++) {
                    board[i][j] = temp[i];
                }
            }
        }

        return board;
    }


    private static int[][] merge(int d, int[][] board) {
        if (d == 0) { // 좌
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n-1; j++) {
                    if (board[i][j] == 0) continue;
                    if (board[i][j] == board[i][j + 1]) {
                        board[i][j] *= 2;
                        board[i][j+1] = 0;
                        j++;
                    }
                }
            }
            return board;
        }
        if (d == 1) { // 우
            for (int i = 0; i < n; i++) {
                for (int j = n-1; j >0; j--) {
                    if (board[i][j] == 0) continue;
                    if (board[i][j] == board[i][j - 1]) {
                        board[i][j] *= 2;
                        board[i][j-1] = 0;
                        j--;
                    }
                }
            }
            return board;
        }
        if (d == 2) { // 상
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n - 1; i++) {
                    if (board[i][j] == 0) continue;
                    if (board[i][j] == board[i + 1][j]) {
                        board[i][j] *= 2;
                        board[i + 1][j] = 0;
                        i++;
                    }
                }
            }
            return board;
        }
        if (d == 3) { // 하
            for (int j = 0; j < n; j++) {
                for (int i = n - 1; i > 0; i--) {
                    if (board[i][j] == 0) continue;
                    if (board[i][j] == board[i - 1][j]) {
                        board[i][j] *= 2;
                        board[i - 1][j] = 0;
                        i--;
                    }
                }
            }
            return board;
        }


        return board;
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] newBoard = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[i][j] = board[i][j];
            }
        }

        return newBoard;
    }
}
