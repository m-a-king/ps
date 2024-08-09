package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1913 {

    static int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    static int[] dy = {0, 1, 0, -1};

    static int n;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        int target = Integer.parseInt(bufferedReader.readLine());

        int[][] map = new int[n][n];

        int x = n / 2; // 시작 위치
        int y = n / 2;
        int value = 1;
        map[x][y] = value; // 시작

        int length = 1;
        int direction = 0;
        int limit = n * n;

        // 결과
        int targetX = -1, targetY = -1;

        while (value < limit) {
            for (int d = 0; d < 2; d++) {
                for (int i = 0; i < length; i++) {
                    x += dx[direction];
                    y += dy[direction];
                    if (value >= limit) break;
                    map[x][y] = ++value;
                }
                direction = (direction + 1) % 4; // 방향 전환
            }
            length++;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(map[i][j]).append(" ");
                if (map[i][j] == target) {
                    targetX = i;
                    targetY = j;
                }
            }
            sb.append("\n");
        }
        sb.append((targetX + 1)).append(" ").append(targetY + 1);

        // 출력
        System.out.print(sb);
    }
}