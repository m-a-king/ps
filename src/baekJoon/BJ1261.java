package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ1261 {

    private static class State {
        int row, col, breakCnt;

        public State(int row, int col, int breakCnt) {
            this.row = row;
            this.col = col;
            this.breakCnt = breakCnt;
        }

    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int row, col;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        col = Integer.parseInt(stringTokenizer.nextToken());
        row = Integer.parseInt(stringTokenizer.nextToken());

        boolean[][] isWall = new boolean[row + 1][col + 1];

        for (int i = 1; i <= row; i++) {
            String input = bufferedReader.readLine();
            for (int j = 1; j <= col; j++) {
                isWall[i][j] = input.charAt(j-1) == '1';
            }
        }

        Deque<State> pq = new ArrayDeque<>();
        boolean[][] visited = new boolean[row + 1][col + 1];
        pq.offer(new State(1, 1, 0));
        visited[1][1] = true;

        while (!pq.isEmpty()) {
            State curr = pq.poll();

            if (curr.row == row && curr.col == col) {
                System.out.println(curr.breakCnt);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nRow = curr.row + dx[d];
                int nCol = curr.col + dy[d];

                if (!isSafe(nRow, nCol)) continue;

                if (visited[nRow][nCol]) continue;
                visited[nRow][nCol] = true;

                if (isWall[nRow][nCol]) {
                    pq.offerLast(new State(nRow, nCol, curr.breakCnt + 1));
                } else {
                    pq.offerFirst(new State(nRow, nCol, curr.breakCnt));
                }

            }
        }
    }

    private static boolean isSafe(int r, int c) {
        return 1 <= r && r <= row && 1 <= c && c <= col;
    }
}
