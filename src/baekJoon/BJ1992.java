package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1992 {

    static boolean[][] map;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        map = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String input = bufferedReader.readLine();

            for (int j = 0; j < n; j++) {
                map[i][j] = input.charAt(j) == '1'; // 1 : true, 0 : false
            }
        }

        compress(0, 0, n);

        System.out.println(stringBuilder);


    }

    private static void compress(int row, int col, int size) {
        boolean first = map[row][col]; // 기준

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (first != map[i][j]) {
                    stringBuilder.append("(");

                    for (int dx = 0; dx < 2; dx++) {
                        for (int dy = 0; dy < 2; dy++) {
                            compress(row + size / 2 * dx, col + size / 2 * dy, size / 2);
                        }
                    }

                    stringBuilder.append(")");
                    return;
                }
            }
        }

        stringBuilder.append(first ? 1 : 0);
//        System.out.println("row = " + row);
//        System.out.println("col = " + col);
//        System.out.println("size = " + size);
//        System.out.println(stringBuilder);

    }
}
