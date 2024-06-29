package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ15683 {

    static class CCTV {
        // 행, 열, cctv 종류
        int row, col, type;

        public CCTV(int row, int col, int type) {
            this.row = row;
            this.col = col;
            this.type = type;
        }
    }

    static List<CCTV> cctvs;
    static int minBlindSpot = Integer.MAX_VALUE; // 정답 (사각지대 최소값)
    static int n, m;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        int[][] map = new int[n][m];
        cctvs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (0 < map[i][j] && map[i][j] < 6) {
                    cctvs.add(new CCTV(i, j, map[i][j]));
                }
            }
        }


        // 0 ~ cctv 개수를 dfs, 백트래킹
        dfs(0, map);

        // 정답 출력
        System.out.println(minBlindSpot);

    }

    private static void dfs(int depth, int[][] currMap) {
        // cctvs 마지막 인덱스 = cctvs.size - 1
        if (depth == cctvs.size()) {
            minBlindSpot = Math.min(minBlindSpot, countBlindSpots(currMap));
            return;
        }

        // 현재 깊이에 맞는 cctv
        CCTV curr = cctvs.get(depth);

        // 0, 90, 180, 270
        int rotationCount = 4;

        // 타입이 2라면, 좌우/상하 (2종류)
        if (curr.type == 2) {
            rotationCount = 2;
        }
        // 타입이 5라면, 상하좌우 (1종류)
        else if (curr.type == 5) {
            rotationCount = 1;
        }

        for (int rotation = 0; rotation < rotationCount; rotation++) {
            int[][] newMap = copyMap(currMap);
            simulate(newMap, curr.row, curr.col, curr.type, rotation);
            dfs(depth + 1, newMap);
        }

    }

    private static void simulate(int[][] newMap, int row, int col, int type, int rotation) {
        switch (type) {
            case 1: // 우 || 하 || 좌 || 상
                watch(newMap, row, col, rotation);
                break;
            case 2: // 우좌 || 하상
                watch(newMap, row, col, rotation);
                watch(newMap, row, col, rotation + 2);
                break;
            case 3: // 우하 || 하좌 || 좌상 || 상우
                watch(newMap, row, col, rotation);
                watch(newMap, row, col, (rotation + 1) % 4);
                break;
            case 4: // 우하좌 || 하좌상 || 좌상우 || 상우하
                watch(newMap, row, col, rotation);
                watch(newMap, row, col, (rotation + 1) % 4);
                watch(newMap, row, col, (rotation + 2) % 4);
                break;
            case 5: // 상하좌우
                watch(newMap, row, col, 0);
                watch(newMap, row, col, 1);
                watch(newMap, row, col, 2);
                watch(newMap, row, col, 3);
                break;
        }
    }

    private static void watch(int[][] map, int row, int col, int rotation) {
        while (true) {
//            rotation
//            0 -> row + 1
//            1 -> col + 1
//            2 -> row - 1
//            3 -> col - 1
            row += dx[rotation];
            col += dy[rotation];

            if (row < 0 || row >= n || col < 0 || col >= m || map[row][col] == 6) break;

            if (map[row][col] == 0) {
                map[row][col] = -1; // 감시 영역 표시, currMap 이 아닌 newMap
            }
        }
    }


    // 기존 맵 복사
    private static int[][] copyMap(int[][] oldMap) {
        int[][] newMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(oldMap[i], 0, newMap[i], 0, m);
        }
        return newMap;
    }

    // 사각지대 탐색
    private static int countBlindSpots(int[][] map) {
        int count = 0;
        for (int[] row : map) {
            for (int cell : row) {
                if (cell == 0) count++;
            }
        }
        return count;
    }
}
