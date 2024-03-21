package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17144 {

    static int[][] map;
    static int row;
    static int col;
    static int purifier1;
    static int purifier2;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        row = Integer.parseInt(stringTokenizer.nextToken());
        col = Integer.parseInt(stringTokenizer.nextToken());
        int time = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[row][col];

        boolean flag = false;

        for (int i = 0; i < row; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (!flag && map[i][j] == -1) {
                    purifier1 = i;
                    purifier2 = i + 1;

                    flag = true;
                }
            }
        }

        for (int i = 0; i < time; i++) {
            spread();
            purifier();
        }

        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res += map[i][j];
            }
        }

        System.out.println(res + 2);
    }

    private static void purifier() {
        // 공기청정기의 입구로 당기는걸 먼저하면 temp가 필요없음

        // 위쪽 공기청정기 반시계방향 순환
        for (int i = purifier1 - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        for (int i = 0; i < col - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        for (int i = 0; i < purifier1; i++) {
            map[i][col - 1] = map[i + 1][col - 1];
        }
        for (int i = col - 1; i > 1; i--) {
            map[purifier1][i] = map[purifier1][i - 1];
        }
        map[purifier1][1] = 0;

        // 아래쪽 공기청정기 시계방향 순환
        // 공기청정기 바로 아래에서 아래로
        for (int i = purifier2 + 1; i < row - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        // 가장 아랫줄에서 왼쪽으로
        for (int i = 0; i < col - 1; i++) {
            map[row - 1][i] = map[row - 1][i + 1];
        }
        // 가장 왼쪽 열에서 위로
        for (int i = row - 1; i > purifier2; i--) {
            map[i][col - 1] = map[i - 1][col - 1];
        }
        // 공기청정기의 수평선에서 오른쪽으로
        for (int i = col - 1; i > 1; i--) {
            map[purifier2][i] = map[purifier2][i - 1];
        }
        map[purifier2][1] = 0;

    }

    private static void spread() {
        int[][] newMap = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int current = map[i][j];
                int count = 0;

                for (int d = 0; d < 4; d++) {
                    int nextRow = i + dx[d];
                    int nextCol = j + dy[d];
                    if (isSafe(nextRow, nextCol)) {
                        count++;
                        newMap[nextRow][nextCol] += current / 5;
                    }
                }
                newMap[i][j] += current - current / 5 * count;

            }
        }

        map = newMap;

    }

    private static boolean isSafe(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < row && 0 <= nextCol && nextCol < col && map[nextRow][nextCol] != -1;
    }
}
