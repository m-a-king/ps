package programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class 리코쳇로봇 {

    public static void main(String[] args) {
        System.out.println(
                new Solution().solution(new String[]{"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."}));
    }

    static class Solution {
        private static final int[] DR = {-1, 1, 0, 0}; // 상, 하, 좌, 우
        private static final int[] DC = {0, 0, -1, 1};

        public int solution(String[] board) {
            int rows = board.length;
            int cols = board[0].length();

            char[][] grid = new char[rows][cols];
            int startR = -1, startC = -1;

            for (int r = 0; r < rows; r++) {
                char[] line = board[r].toCharArray();
                for (int c = 0; c < cols; c++) {
                    grid[r][c] = line[c];
                    if (grid[r][c] == 'R') {
                        startR = r;
                        startC = c;
                    }
                }
            }

            boolean[][] visited = new boolean[rows][cols];
            Queue<Node> queue = new ArrayDeque<>();
            queue.add(new Node(startR, startC, 0));
            visited[startR][startC] = true;

            while (!queue.isEmpty()) {
                Node curr = queue.poll();

                if (grid[curr.row][curr.col] == 'G') {
                    return curr.time;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = curr.row;
                    int nc = curr.col;

                    while (true) {
                        nr += DR[d];
                        nc += DC[d];

                        if (0 > nr || nr >= rows || 0 > nc || nc >= cols) {
                            nr -= DR[d];
                            nc -= DC[d];
                            break;
                        }

                        if (grid[nr][nc] == 'D') {
                            nr -= DR[d];
                            nc -= DC[d];
                            break;
                        }
                    }

                    if (nr == curr.row && nc == curr.col) {
                        continue;
                    }

                    if (visited[nr][nc]) {
                        continue;
                    }
                    visited[curr.row][curr.col] = true;
                    queue.add(new Node(nr, nc, curr.time + 1));
                }
            }
            return -1;
        }

        static class Node {
            int row;
            int col;
            int time;

            public Node(int row, int col, int time) {
                this.row = row;
                this.col = col;
                this.time = time;
            }
        }
    }
}
