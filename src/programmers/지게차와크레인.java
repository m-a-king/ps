package programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class 지게차와크레인 {

    public static void main(String[] args) {
        new Solution().solution(new String[]{"AZWQY", "CAABX", "BBDDA", "ACACA"}, new String[]{"A", "BB", "A"});
    }

    static class Solution {

        static int[] dx = {0, 1, 0, -1};
        static int[] dy = {-1, 0, 1, 0};

        public int solution(String[] storage, String[] requests) {
            int rows = storage.length;
            int cols = storage[0].length();

            int[][] map = new int[rows + 2][cols + 2]; // 1-based & map을 감싸는 빈 칸

            for (int row = 1; row <= rows; row++) {
                for (int col = 1; col <= cols; col++) {
                    map[row][col] = storage[row - 1].charAt(col - 1);
                }
            }

            for (int[] a : map) {
                for (int b : a) {
                    System.out.print(b + " ");
                }
                System.out.println();
            }

            int deletedCount = 0;
            boolean[] visitedChar = new boolean[26];

            for (String request : requests) {

                int target = request.charAt(0);

                if (request.length() == 1) {
                    deletedCount += bfs(rows, cols, map, target);
                }

                if (request.length() == 2) {
                    if (visitedChar[target - 'A']) continue;
                    visitedChar[target - 'A'] = true;
                    for (int i = 0; i < map.length; i++) {
                        for (int j = 0; j < map[0].length; j++) {
                            if (map[i][j] == target) {
                                map[i][j] = 0;
                                deletedCount++;
                            }
                        }
                    }
                }
            }

            System.out.println(deletedCount);

            return rows * cols - deletedCount;
        }

        private int bfs(final int rows, final int cols, final int[][] map, final int target) {
            int deletedCount = 0;

            Queue<int[]> queue = new ArrayDeque<>();

            boolean[][] visited = new boolean[rows + 2][cols + 2];
            queue.add(new int[]{0, 0});
            visited[0][0] = true;

            while (!queue.isEmpty()) {
                int[] point = queue.poll();
                int row = point[0];
                int col = point[1];

                for (int d = 0; d < 4; d++) {
                    int nextRow = row + dx[d];
                    int nextCol = col + dy[d];

                    if (0 > nextRow || nextRow >= map.length ||
                        0 > nextCol || nextCol >= map[0].length) {
                        continue;
                    }

                    if (visited[nextRow][nextCol]) continue;
                    visited[nextRow][nextCol] = true;

                    int nextPoint = map[nextRow][nextCol];
                    if (nextPoint == 0) {
                        queue.add(new int[]{nextRow, nextCol});
                    }
                    if (nextPoint == target) {
                        deletedCount++;
                        map[nextRow][nextCol] = 0;
                    }
                }

            }
            return deletedCount;
        }
    }
}
