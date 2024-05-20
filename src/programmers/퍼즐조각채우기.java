package programmers;

import java.util.*;

public class 퍼즐조각채우기 {

    static class Solution {

        public static void main(String[] args) {
            int[][] game_board = {
                    {0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                    {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1},
                    {0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0},
                    {0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
                    {1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1},
                    {1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0},
                    {0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0},
                    {1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0},
                    {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0},
                    {0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0},
                    {1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0},
                    {0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
                    {0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                    {0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0}};

            int[][] table = {
                    {1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0},
                    {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0},
                    {1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1},
                    {1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1},
                    {1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1},
                    {0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0},
                    {1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1},
                    {0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                    {1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1},
                    {1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                    {1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1},
                    {1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                    {1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                    {0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0},
                    {1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1},
                    {0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1},
                    {0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1}};

            System.out.println(solution(game_board, table));

            //            System.out.println(solution(new int[][]{
//                            {1, 1, 0, 0, 1, 0},
//                            {0, 0, 1, 0, 1, 0},
//                            {0, 1, 1, 0, 0, 1},
//                            {1, 1, 0, 1, 1, 1},
//                            {1, 0, 0, 0, 1, 0},
//                            {0, 1, 1, 1, 0, 0}},
//                    new int[][]{
//                            {1, 0, 0, 1, 1, 0},
//                            {1, 0, 1, 0, 1, 0},
//                            {0, 1, 1, 0, 1, 1},
//                            {0, 0, 1, 0, 0, 0},
//                            {1, 1, 0, 1, 1, 0},
//                            {0, 1, 0, 0, 0, 0}}));
        }

        private static class Pos {
            int row, col;

            public Pos(int row, int col) {
                this.row = row;
                this.col = col;
            }


        }

        static int[] dx = {1, -1, 0, 0};
        static int[] dy = {0, 0, 1, -1};
        static int row, col;

        static List<List<Pos>> blanks = new ArrayList<>();
        static List<List<Pos>> puzzles = new ArrayList<>();
        static int answer = 0;

        static List<Integer> usedPuzzleIdx = new ArrayList<>();


        public static int solution(int[][] game_board, int[][] table) {

            row = game_board.length;
            col = game_board[0].length;

            boolean[][] visitedBoard = new boolean[row][col];
            boolean[][] visitedTable = new boolean[row][col];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (game_board[i][j] == 0 && !visitedBoard[i][j]) {
                        List<Pos> blank = bfs(game_board, i, j, 0, visitedBoard);
                        blanks.add(blank);
                    }

                    if (table[i][j] == 1 && !visitedTable[i][j]) {
                        List<Pos> puzzle = bfs(table, i, j, 1, visitedTable);
                        puzzles.add(puzzle);
                    }
                }
            }


            // 퍼즐 조각들을 게임 보드의 빈 공간에 맞추는 로직 추가
            // 퍼즐 조각의 회전, 비교, 맞추는 로직을 구현

            for (List<Pos> blank : blanks) {
                match(blank);
            }

            for (int idx : usedPuzzleIdx) {
                System.out.println(idx);
            }

            return answer;
        }

        private static void match(List<Pos> blank) {
            for (int puzzleIdx = 0; puzzleIdx < puzzles.size(); puzzleIdx++) {
                if (usedPuzzleIdx.contains(puzzleIdx)) continue;

                List<Pos> puzzle = puzzles.get(puzzleIdx);
                if (puzzle.size() == blank.size()) {

                    for (int rotate = 0; rotate < 4; rotate++) { // 0, 90, 180, 270
                        if (isMatch(blank, puzzle)) {
                            answer += blank.size();
                            System.out.println(answer);
                            usedPuzzleIdx.add(puzzleIdx);
                            return;
                        }
                        puzzle = rotatePuzzle(puzzle);
                    }

                }
            }
        }

        private static boolean isMatch(List<Pos> blank, List<Pos> puzzle) {
            Collections.sort(blank, Comparator.comparingInt((Pos p) -> p.row).thenComparingInt(p -> p.col));
            Collections.sort(puzzle, Comparator.comparingInt((Pos p) -> p.row).thenComparingInt(p -> p.col));

            // 시작점 맞추기
            Pos startBlank = blank.get(0);
            Pos startPuzzle = puzzle.get(0);

            for (int i = 0; i < blank.size(); i++) {
                Pos b = blank.get(i);
                Pos p = puzzle.get(i);
                if ((b.row - startBlank.row) != (p.row - startPuzzle.row) || (b.col - startBlank.col) != (p.col - startPuzzle.col)) {
                    return false;
                }
            }
            return true;
        }

        private static List<Pos> rotatePuzzle(List<Pos> puzzle) {
            List<Pos> rotated = new ArrayList<>();
            for (Pos pos : puzzle) {
                rotated.add(new Pos(pos.col, -pos.row));
            }
            return rotated;
        }

        private static List<Pos> bfs(int[][] board, int startRow, int startCol, int targetValue, boolean[][] visited) {
            Queue<Pos> queue = new ArrayDeque<>();
            queue.offer(new Pos(startRow, startCol));
            List<Pos> piece = new ArrayList<>();
            piece.add(new Pos(startRow - startRow, startCol - startCol)); // 0,0
            visited[startRow][startCol] = true;

            while (!queue.isEmpty()) {
                Pos now = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nextRow = now.row + dx[i];
                    int nextCol = now.col + dy[i];

                    if (isSafe(nextRow, nextCol) && !visited[nextRow][nextCol] && board[nextRow][nextCol] == targetValue) {
                        queue.offer(new Pos(nextRow, nextCol));
                        piece.add(new Pos(nextRow - startRow, nextCol - startCol));
                        visited[nextRow][nextCol] = true;
                    }
                }
            }

            return piece;
        }

        private static boolean isSafe(int nextRow, int nextCol) {
            return 0 <= nextRow && nextRow < row && 0 <= nextCol && nextCol < col;
        }
    }
}
