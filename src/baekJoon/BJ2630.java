package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2630 {

    static int[][] map;
    static int n;

    static int white = 0;
    static int blue = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        splitMap(0, 0, n);

        System.out.println(white);
        System.out.println(blue);
    }

    private static void splitMap(int x, int y, int size) {

        if (checkColor(x, y, size)) {
            if (map[x][y] == 1) {
                blue++;
            } else {
                white++;
            }
        } else {
            size /= 2;
            splitMap(x, y, size);
            splitMap(x + size, y, size);
            splitMap(x, y + size, size);
            splitMap(x + size, y + size, size);
        }
    }

    private static boolean checkColor(int x, int y, int target) {
        int start = map[x][y];

        for (int i = x; i < target+x; i++) {
            for (int j = y; j < target+y; j++) {
                if (map[i][j] != start) {
                    return false;
                }
            }
        }

        return true;
    }
}
