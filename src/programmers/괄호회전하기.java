package programmers;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class 괄호회전하기 {

    public static void main(String[] args) {

        new Solution().solution("[](){}");
    }

    static class Solution {

        static int answer = 0;

        public int solution(String s) {
            Map<Character, Character> op = new HashMap<>();
            op.put(')', '(');
            op.put('}', '{');
            op.put(']', '[');

            for(int i=0;i<s.length();i++) {
                Stack<Character> ts = new Stack<>();
                extracted(i, s, ts, op);
            }

            System.out.println(answer);
            return answer;
        }

        private static void extracted(int push, String s, Stack<Character> ts, Map<Character, Character> op) {
            for (int i = push; i < push + s.length(); i++) {
                System.out.println(ts);
                final char c = s.charAt(i % s.length());

                if (ts.isEmpty()) {
                    ts.push(c);
                }else{
                    if (ts.peek() == op.get(c)) {
                        ts.pop();
                        continue;
                    }
                    ts.push(c);
                }
            }

            if(ts.isEmpty()) {
                answer++;
            }
        }
    }
}
