package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1981 {

    private static class Pos {
        int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n;
    static int left = Integer.MAX_VALUE;
    static int right = 0;
    static int mid = 0;
    static int[][] map;
    static int startValue;
    static int endValue;
    static int minGap;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());

        map = new int[n][n];
        visited = new boolean[n][n];
        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] > max) {
                    max = map[i][j];
                }
                if (map[i][j] < min) {
                    min = map[i][j];
                }
            }
        }

        left = 0;
        right = max - min;

//        System.out.println(left);
//        System.out.println(right);

        startValue = map[0][0];
        endValue = map[n - 1][n - 1];
        minGap = Math.abs(startValue - endValue);

        int answer = Integer.MAX_VALUE;

        while (left <= right) {
            mid = (left + right) / 2;
//            System.out.println("left = " + left);
//            System.out.println("right = " + right);
//            System.out.println("mid = " + mid);
//            System.out.println();

//            if (mid == minGap) {
//                System.out.println("!!!!!!!!!!!!!!!!!!!");
//                System.out.println(mid);
//                return;
//            }

            boolean bfsSuccessCheck = false;
            for (int i = min; i <= max; i++) {
                if (i <= startValue && startValue <= i + mid) {
                    bfsSuccessCheck = bfs(i, i + mid);
                    if (bfsSuccessCheck) {
                        break;
                    }
                }
            }

            // 해당 mid로 탐색이 가능했다면?
            // 기록 갱신 해두기
            // mid 감소 도전 -> right = mid - 1
            if (bfsSuccessCheck) {
//                System.out.println("mid 감소 도전");
                right = mid - 1;
                answer = Math.min(answer, mid);
            }

            // 해당 mid로 탐색이 불가능했다면?
            // mid 증가 필요 -> left = mid + 1
            else {
//                System.out.println("mid 증가 도전");
                left = mid + 1;
            }
        }

        System.out.println(answer);


    }

    private static boolean bfs(int rangeStartPoint, int rangeEndPoint) {
        for (int i = 0; i < n ; i++) {
            Arrays.fill(visited[i], false);
        }

        Queue<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {

            Pos current = queue.poll();

            if (current.row == n - 1 && current.col == n - 1) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = current.row + dx[i];
                int nextCol = current.col + dy[i];

                if (isSafe(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                    int nextVal = map[nextRow][nextCol];

                    if (rangeStartPoint <= nextVal && nextVal <= rangeEndPoint) {
                        queue.offer(new Pos(nextRow, nextCol));
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean isSafe(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < n && 0 <= nextCol && nextCol < n;
    }
}

//        1 1 1 3 1 1 1 2
//        3 3 1 3 1 2 1 2
//        1 1 1 1 1 2 1 2
//        3 3 3 3 3 3 1 2
//        1 1 1 3 3 2 1 2
//        1 2 1 1 1 1 1 2
//        1 3 3 3 3 2 2 2
//        1 1 1 1 1 1 1 1