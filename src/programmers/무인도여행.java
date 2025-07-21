package programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class 무인도여행 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new String[]{"X591X", "X1X5X", "X231X", "1XXX1"})));

    }

    static class Solution {
        static int[] dr = {-1, 1, 0, 0};
        static int[] dc = {0, 0, -1, 1};
        static int rows;
        static int cols;
        static int[][] map;
        static boolean[][] visited;

        public int[] solution(String[] input) {
            rows = input.length;
            cols = input[0].length();
            map = new int[rows][cols];
            visited = new boolean[rows][cols];

            int zeroCount = 0;

            for (int i = 0; i < rows; i++) {
                final char[] line = input[i].toCharArray();
                for (int j = 0; j < cols; j++) {
                    if (line[j] == 'X') {
                        zeroCount++;
                        continue;
                    }
                    map[i][j] = Integer.parseInt(String.valueOf(line[j]));
                }
            }

            List<Integer> accs = new ArrayList<>();

            for (int i = 0; i < input.length; i++) {
                for (int j = 0; j < input[i].length(); j++) {
                    System.out.println(accs);
                    if (visited[i][j]) {
                        continue;
                    }
                    visited[i][j] = true;
                    if (map[i][j] == 0) {
                        continue;
                    }
                    accs.add(bfs(i, j, visited));
                }
            }

            if(zeroCount == rows * cols) {
                return new int[]{-1};
            }

            accs.sort(Integer::compare);
            return accs.stream().mapToInt(i -> i).toArray();
        }

        private int bfs(int i, int j, boolean[][] visited) {
            Queue<Node> queue = new ArrayDeque<>();
            queue.add(new Node(i, j));

            int acc = 0;

            while (!queue.isEmpty()) {
                final Node curr = queue.poll();

                if (map[curr.row][curr.col] == 0) {
                    continue;
                }
                acc += map[curr.row][curr.col];

                for (int d = 0; d < 4; d++) {
                    int nr = curr.row + dr[d];
                    int nc = curr.col + dc[d];

                    if (0 > nr || nr >= rows || 0 > nc || nc >= cols) {
                        continue;
                    }

                    if (visited[nr][nc]) {
                        continue;
                    }
                    visited[nr][nc] = true;

                    queue.add(new Node(nr, nc));
                }
            }

            return acc;
        }

        static class Node {
            int row, col;

            public Node(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }
    }
}
