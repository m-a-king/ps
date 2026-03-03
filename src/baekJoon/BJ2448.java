package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2448 {

    public static final char STAR = '*';
    public static final char BLANK = ' ';
    static char[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());

        map = new char[N][2 * N - 1];

        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], BLANK);
        }

        draw(0, N - 1, N);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void draw(int x, int y, int n) {

        if (n == 3) {
            map[x][y] = STAR;
            map[x + 1][y - 1] = STAR;
            map[x + 1][y + 1] = STAR;
            map[x + 2][y - 2] = STAR;
            map[x + 2][y - 1] = STAR;
            map[x + 2][y] = STAR;
            map[x + 2][y + 1] = STAR;
            map[x + 2][y + 2] = STAR;

            return;
        }

        int nn = n / 2;

        draw(x, y, nn);
        draw(x + nn, y - nn, nn);
        draw(x + nn, y + nn, nn);
    }
}
