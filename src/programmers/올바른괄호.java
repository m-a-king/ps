package programmers;

import java.util.Stack;

public class 올바른괄호 {

    class Solution {
        boolean solution(String s) {
            Stack<String> stack = new Stack<>();


            for (int i = 0; i < s.length(); i++) {
                char bracket = s.charAt(i);

                if (bracket == '(') {
                    stack.push("(");
                } else {
                    if (stack.isEmpty() || !stack.pop().equals("(")) {
                        return false;
                    }
                }
            }

            return stack.isEmpty();

        }
    }
}
