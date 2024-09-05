package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ1918 {

    static char[][] operators;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        char[] input = bufferedReader.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        Stack<Integer> caseStack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();

        operators =
                new char[][]{{'+', '-'},
                        {'*', '/'}};


        for (char c : input) {

            if (Character.isAlphabetic(c)) {
                stringBuilder.append(c);
                continue;
            }

            if (c == '(') {
                caseStack.push(stack.size());
                continue;
            }

            if (c == ')') {
                int target = caseStack.pop();

                while (stack.size() > target) {
                    stringBuilder.append(stack.pop());
                }
                continue;
            }

            int currPriority = getPriority(c);
            int lowerLimit = caseStack.isEmpty() ? 0 : caseStack.peek();

            while (!stack.isEmpty()) {
                char preOperator = stack.peek();
                int prePriority = getPriority(preOperator);

                if (stack.size() > lowerLimit && prePriority >= currPriority) {
                    stringBuilder.append(stack.pop());
                } else {
                    break;
                }
            }

            stack.push(c);
        }

        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }

        System.out.println(stringBuilder);

    }

    private static int getPriority(char operator) {
        for (int i = 0; i < 2; i++) {
            if (operators[i][0] == operator || operators[i][1] == operator) {
                return i;
            }
        }

        return -1;
    }
}
