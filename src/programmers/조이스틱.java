package programmers;

public class 조이스틱 {

    static class Solution {

        public static void main(String[] args) {
            System.out.println(solution("JAN"));
        }



        public static int solution(String name) {
            int answer = 0;
            int changeCount = 0;
            int moveCount = name.length() - 1;

            for (int i = 0; i < name.length(); i++) {
                changeCount += changeChar(name.charAt(i));

                int check = i + 1;
                while (check < name.length() && name.charAt(check) == 'A') {
                    check++;
                }

                moveCount = Math.min(moveCount, Math.min(i * 2 + name.length() - check, (name.length() - check) * 2 + i));

            }

            answer = changeCount + moveCount;

            return answer;
        }

        private static int changeChar(char alp) {
            int forwardCount = alp - 'A';
            int reverseCount = 'Z' - alp + 1;
            return Math.min(forwardCount, reverseCount);
        }

    }
}

