package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BJ11660 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");

        int n = parseInt(nm[0]);
        int m = parseInt(nm[1]);

        int[][] board = new int[n + 1][n + 1];
        int[][] sum = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            int[] row = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j <= n; j++) {
                board[i][j] = row[j - 1];
                sum[i][j] = board[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }

        for (int i = 0; i < m; i++) {
            int[] xy = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int x1 = xy[1];
            int y1 = xy[0];
            int x2 = xy[3];
            int y2 = xy[2];

            System.out.println(calcSum(x1, y1, x2, y2, sum));

        }
    }

    private static int calcSum(int x1, int y1, int x2, int y2, int[][] sum) {


        return sum[y2][x2] - sum[y1 - 1][x2] - sum[y2][x1 - 1] + sum[y1 - 1][x1 - 1];
    }


}
