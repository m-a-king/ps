package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2583 {

    private static class Pos {
        int y, x;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int n;
    static int m;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        m = Integer.parseInt(stringTokenizer.nextToken());
        n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());

        int[][] map = new int[m][n];


        for (int i = 0; i < k; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            int lbx = Integer.parseInt(stringTokenizer.nextToken());
            int lby = Integer.parseInt(stringTokenizer.nextToken());
            int rtx = Integer.parseInt(stringTokenizer.nextToken());
            int rty = Integer.parseInt(stringTokenizer.nextToken());

            for (int y = lby; y < rty; y++) {
                for (int x = lbx; x < rtx; x++) {
                    map[y][x] = 1;

                }
            }
        }

        Queue<Pos> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        List<Integer> subCounts = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] != 1) {
                    count++;
                    int areaSize = 0;

                    visited[i][j] = true;
                    queue.offer(new Pos(j, i));

                    while (!queue.isEmpty()) {
                        Pos current = queue.poll();
                        areaSize++;

                        for (int d = 0; d < 4; d++) {
                            int nextX = current.x + dx[d];
                            int nextY = current.y + dy[d];

                            if (isSafe(nextX, nextY) && !visited[nextY][nextX] && map[nextY][nextX] != 1) {
                                visited[nextY][nextX] = true;
                                queue.offer(new Pos(nextX, nextY));
                            }
                        }

                    }
                    subCounts.add(areaSize); // 수정된 영역 크기를 추가

                }
            }
        }

        System.out.println(count);

        Collections.sort(subCounts);

        for (int subCount : subCounts) {
            System.out.print(subCount);
            System.out.print(" ");
        }
    }

    private static boolean isSafe(int nextX, int nextY) {
        return 0 <= nextX && nextX < n && 0 <= nextY && nextY < m;
    }

}

