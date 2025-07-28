package programmers;

import java.util.Stack;

public class 짝지어제거하기 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution("baabaa"));
    }

    static class Solution {
        public int solution(String s) {

            Stack<Character> stack = new Stack<>();
            for (char c : s.toCharArray()) {
                if (stack.isEmpty() || c != stack.peek()) {
                    stack.push(c);
                    continue;
                }
                stack.pop();
            }

            return stack.isEmpty() ? 1 : 0;
        }
    }
}
