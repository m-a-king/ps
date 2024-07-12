package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17779 {
    static int n;
    static int[][] map;
    static int totalArea = 0;
    static int minDiff = Integer.MAX_VALUE;
    static int[][] areaChecker;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());

        map = new int[n + 1][n + 1]; // n * n

        for (int i = 1; i <= n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                totalArea += map[i][j];
            }
        }

        simulate();

        System.out.println(minDiff);
    }

    private static void simulate() {
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                for (int d1 = 1; d1 <= n; d1++) {
                    for (int d2 = 1; d2 <= n; d2++) {
                        if (isValid(x, y, d1, d2)) {
                            division(x, y, d1, d2);
                        }
                    }
                }
            }
        }
    }

    private static void division(int x, int y, int d1, int d2) {
        areaChecker = new int[n + 1][n + 1];

        setBoundary(x, y, d1, d2);
        fillArea5(x, y, d1, d2);

        int[] areas = new int[6];
        areas[1] = calcArea(1, x + d1 - 1, 1, y);
        areas[2] = calcArea(1, x + d2, y + 1, n);
        areas[3] = calcArea(x + d1, n, 1, y - d1 + d2 - 1);
        areas[4] = calcArea(x + d2 + 1, n, y - d1 + d2, n);
        areas[5] = totalArea - (areas[1] + areas[2] + areas[3] + areas[4]);

        // 현재 구역을 나눴을 때, 최소 최대 차이 구하기
        int currMin = Integer.MAX_VALUE;
        int currMax = Integer.MIN_VALUE;
        for (int i = 1; i <= 5; i++) {
            currMin = Math.min(currMin, areas[i]);
            currMax = Math.max(currMax, areas[i]);
        }

        minDiff = Math.min(minDiff, currMax - currMin);
    }

    // 구역 내부 사이즈 구하기
    private static int calcArea(int sx, int ex, int sy, int ey) {
        int currArea = 0;

        for (int i = sx; i <= ex; i++) {
            for (int j = sy; j <= ey; j++) {
                // 5번 구역이면 해당 열은 제외
                if (areaChecker[i][j] == 5) continue;
                currArea += map[i][j];
            }
        }

        return currArea;
    }

    // 5번 구역 경계를 따라서 5번 구역 채우기
    private static void fillArea5(int x, int y, int d1, int d2) {
        for (int i = x + 1; i < x + d1 + d2; i++) { // 맨 위와 맨 아래 제외
            boolean boundaryChecker = false;
            for (int j = 1; j <= n; j++) { // 해당 행의 전체 열 검사
                if (areaChecker[i][j] == 5) boundaryChecker = !boundaryChecker;
                if (boundaryChecker) areaChecker[i][j] = 5;
            }

        }

//        System.out.println();
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j++) {
//                System.out.print(areaChecker[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    // 5번 구역 경계선 설정
    private static void setBoundary(int x, int y, int d1, int d2) {
        for (int i = 0; i <= d1; i++) {
            // 1. 상 -> 좌
            areaChecker[x + i][y - i] = 5;

            // 3. 하 -> 우
            areaChecker[x + d2 + i][y + d2 - i] = 5;
        }
        for (int i = 0; i <= d2; i++) {
            // 4. 우-> 상
            areaChecker[x + i][y + i] = 5;

            // 2. 좌 -> 하
            areaChecker[x + d1 + i][y - d1 + i] = 5;
        }
    }

    // 문제 조건 따라적음
    private static boolean isValid(int x, int y, int d1, int d2) {
        return (x + d1 + d2 <= n) && (1 <= y - d1) && (y + d2 <= n);
    }
}
