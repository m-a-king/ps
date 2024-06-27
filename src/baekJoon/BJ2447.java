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
        map = new boolean[n][n];

        drawMap(n, 0, 0);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                stringBuilder.append(map[i][j] ? " " : "*");
            }
            stringBuilder.append("\n");
        }

        System.out.print(stringBuilder.toString().trim());

    }

    private static void drawMap(int n, int x, int y) {

        if (n == 1) {
            return;
        }

        int count = 0;
        for (int i = x; i < x + n; i += n / 3) {
            for (int j = y; j < y + n; j += n / 3) {
                if (++count == 5) {
                    for (int si = i; si < i + n / 3; si++) {
                        for (int sj = j; sj < j + n / 3; sj++) {
                            map[si][sj] = true;
                        }
                    }
                }
                drawMap(n / 3, i, j); // 분할
            }
        }
    }


}
