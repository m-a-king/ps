package programmers;

public class 모음사전 {

    static class Solution {

        public static void main(String[] args) {
            System.out.println(solution("IE"));
        }

        public static int solution(String word) {
            int answer = 0;

            // 입력값과 AAAAA와의 차이를 저장하는 변수
            int diffWithAAAAA = 0;

            // 입력값의 자릿수만큼 알파벳을 숫자로 변환
            for (int i = 0; i < word.length(); i++) {
                // 다음 자릿수로 변경 (i=0 제외)
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

            // 입력값의 자릿수가 5자리가 아니라면 A로 채운다고 가정 (빌림)
            for (int i = 0; i < 5 - word.length(); i++) {
                diffWithAAAAA *= 10;
            }

            // 6진수의 개념으로 풀이
            // 특정한 자릿수만 변경되고, 나머지가 그대로 유지되기 위한 값을 계산함.
            // 1, 6, 31, 156, 781
            int[] digits = new int[5];
            digits[0] = 1;
            for (int i = 1; i < 5; i++) {
                digits[i] = digits[i - 1] * 5 + 1;
            }

            // 계산된 각 자릿수의 차이가 어느정도인지 digits를 이용해 정답 계산
            for (int i = 0; i < 5; i++) {
                int now = diffWithAAAAA % 10;
                answer += now * digits[i];
                diffWithAAAAA /= 10;
            }

            // 정답 : 계산된 차이 + 5(AAAAA(5)와 비교했으므로) - 빌린 값
            // word.length = (5 - 빌린 값)
            return answer + word.length();
        }
    }
}
