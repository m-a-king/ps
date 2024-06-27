package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2447 {

    static int n;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        map = new boolean[n + 1][n + 1];

        drawMap(n, 1, 1);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                stringBuilder.append(map[i][j] ? "*" : " ");
            }
            stringBuilder.append("\n");
        }

        System.out.print(stringBuilder.toString().trim());

    }

    private static void drawMap(int n, int x, int y) {

        if (n == 1) {
            map[x][y] = true;
            return;
        }

        int count = 0;
        for (int i = x; i < x + n; i += n / 3) {
            for (int j = y; j < y + n; j += n / 3) {
                count++;
                if (count == 5) {
                    continue; // 0
                }
                drawMap(n / 3, i, j); // 1
            }
        }
    }


}
