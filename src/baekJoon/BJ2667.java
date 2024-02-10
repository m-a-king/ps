package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BJ2667 {

    private static class Pos {
        int x, y, cost;

        public Pos(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    private static int[][] map;
    private static boolean[][] visited;

    private static int N;
    private static Queue<Pos> queue;
    private static List<Integer> counter = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(bufferedReader.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(bufferedReader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        queue = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isSafe(i, j)) {
                    visited[i][j] = true;
                    bfs(i, j);
                }
            }
        }

        System.out.println(counter.size());
        Collections.sort(counter);
        for (int count : counter) {
            System.out.println(count);

        }
    }

    private static void bfs(int x, int y) {
        int count = 0;
        queue.offer(new Pos(x, y, 1));

        while (!queue.isEmpty()) {
            Pos current = queue.poll();
            count = Math.max(count, current.cost);

            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];

                if (isSafe(nextX, nextY)) {
                    visited[nextX][nextY] = true;
                    queue.offer(new Pos(nextX, nextY, ++count));
                }
            }
        }

        counter.add(count);

    }

    private static boolean isSafe(int i, int j) {

        return 0 <= i && i < N && 0 <= j && j < N && !visited[i][j] && map[i][j] == 1;
    }


}
