package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BJ1012 {

    private static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int M;
    static int N;
    static boolean[][] map = new boolean[M][N];


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = parseInt(bufferedReader.readLine());

        for (int tc = 0; tc < T; tc++) {
            String[] MNK = bufferedReader.readLine().split(" ");
            M = parseInt(MNK[0]);
            N = parseInt(MNK[1]);
            int K = parseInt(MNK[2]);

            map = new boolean[M][N];

            for (int i = 0; i < K; i++) {
                String[] XY = bufferedReader.readLine().split(" ");
                int X = parseInt(XY[0]);
                int Y = parseInt(XY[1]);

                map[X][Y] = true;
            }

            Queue<Pos> queue = new ArrayDeque<>();
            int count = 0;

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j]) {
                        count++;
                        map[i][j] = false;

                        queue.offer(new Pos(i, j));

                        while (!queue.isEmpty()) {
                            Pos current = queue.poll();

                            int[] dx = {1, -1, 0, 0};
                            int[] dy = {0, 0, 1, -1};

                            for (int d = 0; d < 4; d++) {
                                int nextX = current.x + dx[d];
                                int nextY = current.y + dy[d];

                                if (isSafe(nextX, nextY)) {
                                    map[nextX][nextY] = false;
                                    queue.offer(new Pos(nextX, nextY));

                                }
                            }

                        }


                    }
                }
            }

            System.out.println(count);
        }
    }

    private static boolean isSafe(int nextX, int nextY) {
        return 0 <= nextX && nextX < M && 0 <= nextY && nextY < N && map[nextX][nextY];
    }
}
