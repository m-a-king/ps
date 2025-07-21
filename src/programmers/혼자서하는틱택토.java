package programmers;

public class 혼자서하는틱택토 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new String[]{"XXO", ".OX", "O.."}));

    }

    static class Solution {

        static int countO = 0;
        static int countX = 0;
        static char[][] map = new char[3][3];

        public int solution(String[] input) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    final char c = input[i].charAt(j);
                    if (c == 'O') {
                        countO++;
                    } else if (c == 'X') {
                        countX++;
                    }

                    map[i][j] = c;
                }
            }

            if (countO < countX) {
                return 0;
            }

            if (countO - countX > 1) {
                return 0;
            }

            if (countO == countX && !isWin('O')) {
                return 1;
            }

            if (countO == countX + 1 && !isWin('X')) {
                return 1;
            }

            return 0;
        }

        private boolean isWin(char target) {
            if (target == 'O' && countO < 3) {
                return false;
            }

            if (target == 'X' && countX < 3) {
                return false;
            }

            for (int i = 0; i < 3; i++) {
                int count = 0;
                for (int j = 0; j < 3; j++) {
                    if (map[i][j] == target) {
                        count++;
                    }
                }

                if (count == 3) {
                    return true;
                }
            }

            for (int i = 0; i < 3; i++) {
                int count = 0;
                for (int j = 0; j < 3; j++) {
                    if (map[j][i] != target) {
                        count++;
                    }
                }

                if (count == 3) {
                    return true;
                }
            }

            if (map[0][0] == target && map[1][1] == target && map[2][2] == target) {
                return true;
            }

            if (map[0][2] == target && map[1][1] == target && map[2][0] == target) {
                return true;
            }

            return false;
        }
    }
}
