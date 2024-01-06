package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Math.min;

public class BJ1018 {

    static String[][] board;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = nm[0];
        int m = nm[1];

        board = new String[n][m];

        for (int i = 0; i < n; i++) {
            String[] line = bufferedReader.readLine().split("");
            System.arraycopy(line, 0, board[i], 0, m);
        }

        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j <= m - 8; j++) {
                String startPoint = board[i][j];
                checkBoard(i, j, "W");
                checkBoard(i, j, "B");
            }
        }

        System.out.println(min);
    }

    private static void checkBoard(int i, int j, String startColor) {
        int count = 0;
        String currentColor = startColor;

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (!board[i + x][j + y].equals(currentColor)) {
                    count++;
                }

                currentColor = currentColor.equals("W") ? "B" : "W";

                if (count > min) {
                    return;
                }
            }
            currentColor = currentColor.equals("W") ? "B" : "W";
        }

        min = count;
    }
}
