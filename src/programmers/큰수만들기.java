package programmers;

import java.util.Stack;

public class 큰수만들기 {

    public static void main(String[] args) {
        Solution.solution("9876543214", 4);
    }

    static private class Solution {

        public static String solution(String number, int k) {
            String answer = "";

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < number.length(); i++) {
                char curr = number.charAt(i);

                while (k > 0 && stringBuilder.length() > 0 && stringBuilder.charAt(stringBuilder.length() - 1) < curr) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    k--;
                }

                stringBuilder.append(curr);
            }

            while (k != 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                k--;
            }
            System.out.println(stringBuilder);

            return stringBuilder.toString();
        }
    }
}
