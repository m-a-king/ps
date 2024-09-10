package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17070 {

    private static class Pipe {
        int row, col;
        int shape; // 1 -> 가로, 2 -> 세로, 3 -> 대각선

        public Pipe(int row, int col, int shape) {
            this.row = row;
            this.col = col;
            this.shape = shape;
        }
    }

    static int[][] map;
    static int[][] visited; // 3차원 배열로 방문 상태를 관리
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());

        map = new int[n + 1][n + 1];
        visited = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        if(map[n][n] == 1) {
            System.out.println(0);
            return ;
        }

        Queue<Pipe> queue = new ArrayDeque<>();
        queue.offer(new Pipe(1, 2, 1)); // 시작 파이프는 가로 형태
        int answer = 0;

        while (!queue.isEmpty()) {
            Pipe curr = queue.poll();

            int currRow = curr.row;
            int currCol = curr.col;
            int currShape = curr.shape;

            if (currRow == n && currCol == n) {
                answer++;
                continue;
            }

            // 현재 파이프의 형태가 가로(1)일 때
            if (currShape == 1) {
                if (currCol + 1 <= n && map[currRow][currCol + 1] == 0) {
                    queue.offer(new Pipe(currRow, currCol + 1, 1));
                }
            }

            // 현재 파이프의 형태가 세로(2)일 때
            if (currShape == 2) {
                if (currRow + 1 <= n && map[currRow + 1][currCol] == 0) {
                    queue.offer(new Pipe(currRow + 1, currCol, 2));
                }
            }

            // 현재 파이프의 형태가 대각선(3)일 때
            if (currShape == 3) {
                if (currCol + 1 <= n && map[currRow][currCol + 1] == 0) {
                    queue.offer(new Pipe(currRow, currCol + 1, 1));
                }
                if (currRow + 1 <= n && map[currRow + 1][currCol] == 0) {
                    queue.offer(new Pipe(currRow + 1, currCol, 2));
                }
            }

            // 모든 형태에서 대각선 이동 가능성 체크 (공통으로 수행)
            if (currRow + 1 <= n && currCol + 1 <= n && map[currRow + 1][currCol] == 0 && map[currRow][currCol + 1] == 0 && map[currRow + 1][currCol + 1] == 0) {
                queue.offer(new Pipe(currRow + 1, currCol + 1, 3));
            }
        }

        System.out.println(answer);
    }
}
