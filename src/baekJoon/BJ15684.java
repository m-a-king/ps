package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15684 {
    static int n, m, h;

    static boolean[][] hMap;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        n = Integer.parseInt(stringTokenizer.nextToken()); // 세로 축 개수
        m = Integer.parseInt(stringTokenizer.nextToken()); // 기존의 가로 선 개수
        h = Integer.parseInt(stringTokenizer.nextToken()); // 가로 축 개수 (점선 축 개수)
        if (m == 0) {
            System.out.println(0);
            return;
        }

        // row: 1~h
        // col: 1~(n-1)
        hMap = new boolean[h + 1][n];

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int row = Integer.parseInt(stringTokenizer.nextToken());
            int col = Integer.parseInt(stringTokenizer.nextToken());

            // row 행의
            // col 열과 col+1 열이 연결됨
            hMap[row][col] = true;
        }

        for (int limit = 0; limit <= 3; limit++) {
            if (simulate(0, 1, 1, limit)) {
                System.out.println(limit);
                return;
            }
        }

        System.out.println(-1);

    }

    private static boolean simulate(int depth, int sRow, int sCol, int limit) {
        if (depth == limit) {
            return check();
        }

        for (int i = sRow; i <= h; i++) {
            for (int j = (i == sRow ? sCol : 1); j < n; j++) {
                if (hMap[i][j]) continue;
                if (1 < j && hMap[i][j - 1]) continue;
                if (j < n - 1 && hMap[i][j + 1]) continue;

                hMap[i][j] = true;
                if (simulate(depth + 1, i, j + 1, limit)) {
                    return true;
                }
                hMap[i][j] = false;
            }
        }

        return false;
    }

    private static boolean check() {
        for (int start = 1; start <= n; start++) {

            int col = start; // 시작점
            for (int row = 1; row <= h; row++) {
                if (col < n && hMap[row][col]) {
                    col++;
                } else if (col > 1 && hMap[row][col - 1]) {
                    col--;
                }
            }

            if (start != col) {
                return false;
            }
        }
        return true;
    }

    private static void printMap() {
        for (int i = 1; i < hMap.length; i++) {
            for (int j = 1; j < hMap[0].length; j++) {
                System.out.print(" | " + (hMap[i][j] ? "--" : "  "));

            }
            System.out.print(" | ");
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
