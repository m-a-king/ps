package programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class 수레움직이기 {

    public static void main(String[] args) {

//        System.out.println("answer: " + solution(new int[][]{{1, 0, 2}, {0, 0, 5}, {5, 0, 5}, {4, 0, 3}}));
        System.out.println("answer: " + solution(new int[][]{{1, 5}, {2, 5}, {4, 5}, {3, 5}}));

    }

    static int row, col;

    private static class Pos {
        int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    private static class PosSet {
        Pos red, blue;
        boolean[][] visitedRed, visitedBlue;
        int count = 0;

        public PosSet(Pos red, Pos blue, boolean[][] visitedRed, boolean[][] visitedBlue, int count) {
            this.red = red;
            this.blue = blue;
            this.visitedRed = visitedRed;
            this.visitedBlue = visitedBlue;
            this.count = count;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;

    public static int solution(int[][] maze) {
        map = maze;
        row = maze.length;
        col = maze[0].length;

        Pos startRed = null, targetRed = null, startBlue = null, targetBlue = null;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (maze[i][j] == 0) continue;

                if (maze[i][j] == 1) {
                    startRed = new Pos(i, j);
                } else if (maze[i][j] == 2) {
                    startBlue = new Pos(i, j);
                } else if (maze[i][j] == 3) {
                    targetRed = new Pos(i, j);
                } else if (maze[i][j] == 4) {
                    targetBlue = new Pos(i, j);
                }
            }
        }

        Queue<PosSet> queue = new ArrayDeque<>();
        boolean[][] initialVisitedRed = new boolean[row][col];
        boolean[][] initialVisitedBlue = new boolean[row][col];

        initialVisitedRed[startRed.row][startRed.col] = true;
        initialVisitedBlue[startBlue.row][startBlue.col] = true;

        queue.offer(new PosSet(startRed, startBlue, initialVisitedRed, initialVisitedBlue, 0));

        while (!queue.isEmpty()) {
            PosSet curr = queue.poll();

            Pos red = curr.red;
            Pos blue = curr.blue;

            System.out.println("red = " + red);
            System.out.println("blue = " + blue);
            System.out.println();

            assert targetRed != null;
            boolean isFinishRed = red.row == targetRed.row && red.col == targetRed.col;
            assert targetBlue != null;
            boolean isFinishBlue = blue.row == targetBlue.row && blue.col == targetBlue.col;

            if (isFinishRed && isFinishBlue) {
                return curr.count;
            }

            for (int i = 0; i < 4; i++) {
                Pos nextRed;
                if (!isFinishRed) {
                    nextRed = new Pos(red.row + dx[i], red.col + dy[i]);

                    System.out.println("nextRed = " + nextRed);

                    if (!isSafe(nextRed)) continue;
                    if (curr.visitedRed[nextRed.row][nextRed.col]) continue;
                    if (map[nextRed.row][nextRed.col] == 5) continue;

                } else {
                    nextRed = curr.red;

                    System.out.println("nextRed = " + nextRed);
                }
                for (int j = 0; j < 4; j++) {
                    Pos nextBlue;
                    if (!isFinishBlue) {
                        nextBlue = new Pos(blue.row + dx[j], blue.col + dy[j]);

                        System.out.println("nextBlue = " + nextBlue);

                        if (!isSafe(nextBlue)) continue;
                        if (curr.visitedBlue[nextBlue.row][nextBlue.col]) continue;
                        if (map[nextBlue.row][nextBlue.col] == 5) continue;
                    } else {
                        nextBlue = curr.blue;

                        System.out.println("nextBlue = " + nextBlue);
                    }

                    if (nextRed.row == nextBlue.row && nextRed.col == nextBlue.col) continue;
                    if (nextRed.row == blue.row && nextRed.col == blue.col && nextBlue.row == red.row && nextBlue.col == red.col)
                        continue;

                    // visitedRed 배열 깊은 복사
                    boolean[][] visitedRed = new boolean[row][col];
                    for (int k = 0; k < visitedRed.length; k++) {
                        visitedRed[k] = curr.visitedRed[k].clone();
                    }

                    // visitedBlue 배열 깊은 복사
                    boolean[][] visitedBlue = new boolean[row][col];
                    for (int k = 0; k < visitedBlue.length; k++) {
                        visitedBlue[k] = curr.visitedBlue[k].clone();
                    }

                    if (!isFinishRed) {
                        visitedRed[nextRed.row][nextRed.col] = true;
                        System.out.println("isFinishRed = " + isFinishRed);
                    }
                    if (!isFinishBlue) {
                        visitedBlue[nextBlue.row][nextBlue.col] = true;
                        System.out.println("isFinishBlue = " + isFinishBlue);
                    }

                    queue.offer(new PosSet(nextRed, nextBlue, visitedRed, visitedBlue, curr.count + 1));
                    System.out.println("삽입");
                }
            }
        }

        System.out.println("asdasd");
        return 0;
    }

    private static boolean isSafe(Pos pos) {
        return 0 <= pos.row && pos.row < row && 0 <= pos.col && pos.col < col;
    }


}
