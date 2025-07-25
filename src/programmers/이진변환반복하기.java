package programmers;

public class 이진변환반복하기 {

    public static void main(String[] args) {
        new Solution().solution("110010101001");

    }

    static class Solution {
        public int[] solution(String s) {
            int[] answer = new int[2];

            while (!s.equals("1")) {
                int sLength = s.length();
                String replaceS = s.replace("0", "");

                int changeLength = replaceS.length();
                answer[1] += sLength - changeLength;

                s = Integer.toBinaryString(changeLength);

                answer[0]++;
            }

            return answer;
        }
    }
}
