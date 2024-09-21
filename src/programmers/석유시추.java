package programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class 석유시추 {

    public static void main(String[] args) {
        int[][] land = new int[][]{{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
//        int[][] land2 = new int[][]{{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}};
        System.out.println("answer = " + solution(land));

    }

    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int rowSize, colSize;
    static int[][] map;

    static int[] colValue;

    public static int solution(int[][] land) {
        int answer = 0;
        map = land;
        rowSize = land.length;
        colSize = land[0].length;
        colValue = new int[colSize];

        visited = new boolean[rowSize][colSize];

        int k = 0;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (visited[i][j]) continue;
                if (land[i][j] == 0) continue;

                System.out.println("i = " + i);
                System.out.println("j = " + j);
                bfs(i, j, k++);
            }
        }

        for (int cv : colValue) {
            System.out.println("cv = " + cv);
            answer = Math.max(answer, cv);
        }

        return answer;
    }

    private static void bfs(int i, int j, int k) {

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{i, j});
        visited[i][j] = true;

        int currSize = 1;
        int left = j;
        int right = j;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cx = curr[0];
            int cy = curr[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isSafe(nx, ny)) {
                    if (visited[nx][ny]) continue;
                    visited[nx][ny] = true;

                    if (map[nx][ny] == 0) continue;

                    queue.offer(new int[]{nx, ny});

                    currSize++;
                    left = Math.min(left, ny);
                    right = Math.max(right, ny);
                }
            }
        }

        System.out.println("k = " + k);
        System.out.println("left = " + left);
        System.out.println("right = " + right);
        System.out.println("currSize = " + currSize);
        System.out.println();
        System.out.println();

        for (int c = left; c <= right; c++) {
            colValue[c] += currSize;
        }
    }

    private static boolean isSafe(int nx, int ny) {
        return 0 <= nx && nx < rowSize && 0 <= ny && ny < colSize;
    }
}
