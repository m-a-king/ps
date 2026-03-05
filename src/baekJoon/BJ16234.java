package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16234 {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N;
    static int L;
    static int R;
    static int[][] map;
    static boolean[][] visited;
    static int[][] nextMap;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        L = Integer.parseInt(stringTokenizer.nextToken());
        R = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int day = 0;

        while (true) {
            visited = new boolean[N][N];
            nextMap = new int[N][];
            for (int i = 0; i < N; i++) nextMap[i] = map[i].clone();
            boolean change = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) {
                        continue;
                    }
                    visited[i][j] = true;

                    int changeCount = calc(i, j);
                    if (changeCount > 1) {
                        change = true;
                    }
                }
            }

            if (!change) {
                break;
            }
            day++;
            map = nextMap;
        }

        System.out.println(day);

    }

    private static int calc(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        List<int[]> union = new ArrayList<>();
        q.offer(new int[]{x, y});

        int acc = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int currVal = map[curr[0]][curr[1]];

            union.add(curr);
            acc += currVal;

            for (int d = 0; d < 4; d++) {
                final int nx = curr[0] + dx[d];
                final int ny = curr[1] + dy[d];

                final boolean safe = 0 <= nx && nx < N && 0 <= ny && ny < N;

                if (!safe) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                final int diff = Math.abs(currVal - map[nx][ny]);
                if (L <= diff && diff <= R) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        int avg = acc / union.size();

        for (int[] cell : union) {
            nextMap[cell[0]][cell[1]] = avg;
        }

        return union.size();
    }
}
