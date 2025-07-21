package programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class 미로탈출 {

    public static void main(String[] args) {
        final int answer = new Solution().solution(new String[]{"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"});
        System.out.println(answer);

    }

    static class Solution {
        static int[] DR = {1, -1, 0, 0};
        static int[] DC = {0, 0, 1, -1};

        public int solution(String[] input) {
            final int rows = input.length;
            final int cols = input[0].length();
            boolean[][] map = new boolean[rows][cols];

            int[] start = new int[2];
            int[] middle = new int[2];
            int[] end = new int[2];

            for (int row = 0; row < rows; row++) {
                char[] line = input[row].toCharArray();
                for (int col = 0; col < input[row].length(); col++) {
                    if (line[col] == 'O') {
                        map[row][col] = true;
                    } else if (line[col] == 'X') {
                        map[row][col] = false;
                    } else if (line[col] == 'S') {
                        map[row][col] = true;
                        start[0] = row;
                        start[1] = col;
                    } else if (line[col] == 'L') {
                        map[row][col] = true;
                        middle[0] = row;
                        middle[1] = col;
                    } else if (line[col] == 'E') {
                        map[row][col] = true;
                        end[0] = row;
                        end[1] = col;
                    }
                }
            }

            boolean[][] visited = new boolean[rows][cols];
            boolean[][] visitedForLever = new boolean[rows][cols];

            Queue<Node> queue = new ArrayDeque<>();
            queue.add(new Node(start[0], start[1], 0, false));
            visited[start[0]][start[1]] = true;

            while (!queue.isEmpty()) {
                Node curr = queue.poll();

                if (curr.row == middle[0] && curr.col == middle[1]) {
                    System.out.println("curr.row = " + curr.row);
                    System.out.println("curr.col = " + curr.col);
                    System.out.println("curr.time.lever.flag = " + curr.time);
                    curr.leverFlag = true;
                }

                if (curr.leverFlag && curr.row == end[0] && curr.col == end[1]) {
                    System.out.println("curr.row = " + curr.row);
                    System.out.println("curr.col = " + curr.col);
                    return curr.time;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = curr.row + DR[d];
                    int nc = curr.col + DC[d];

                    if (0 > nr || nr >= rows || 0 > nc || nc >= cols) {
                        continue;
                    }

                    if (!map[nr][nc]) {
                        System.out.println("nr = " + nr);
                        System.out.println("nc = " + nc);
                        continue;
                    }

                    if (!curr.leverFlag) {
                        if (visited[nr][nc]) {
                            continue;
                        }
                        visited[nr][nc] = true;
                    } else {
                        if (visitedForLever[nr][nc]) {
                            continue;
                        }
                        visitedForLever[nr][nc] = true;
                    }

                    queue.add(new Node(nr, nc, curr.time + 1, curr.leverFlag));
                }
            }
            return -1;
        }

        static class Node {
            int row, col, time;
            boolean leverFlag = false;

            public Node(int row, int col, int time, boolean leverFlag) {
                this.row = row;
                this.col = col;
                this.time = time;
                this.leverFlag = leverFlag;
            }
        }
    }
}
