package programmers;

public class 모음사전 {

    static class Solution {

        public static void main(String[] args) {
            System.out.println(solution("IE"));
        }

        public static int solution(String word) {
            int answer = 0;

            int diffWithAAAAA = 0;

            for (int i = 0; i < word.length(); i++) {
                diffWithAAAAA *= 10;

                switch (word.charAt(i)) {
                    case 'A' -> {
                        diffWithAAAAA += 0;
                    }
                    case 'E' -> {
                        diffWithAAAAA += 1;
                    }
                    case 'I' -> {
                        diffWithAAAAA += 2;
                    }
                    case 'O' -> {
                        diffWithAAAAA += 3;
                    }
                    case 'U' -> {
                        diffWithAAAAA += 4;
                    }
                }
            }

            for (int i = 0; i < 5 - word.length(); i++) {
                diffWithAAAAA *= 10;
            }

            // 6진수의 개념으로 풀이
            int[] digits = new int[5];
            digits[0] = 1;
            for (int i = 1; i < 5; i++) {
                digits[i] = digits[i - 1] * 5 + 1;
            }

            for (int i = 0; i < 5; i++) {
                int now = diffWithAAAAA % 10;
                answer += now * digits[i];
                diffWithAAAAA /= 10;
            }

            return answer + word.length();
        }
    }
}
