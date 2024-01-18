package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import static java.lang.Integer.parseInt;

public class BJ10828 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            String[] input = bufferedReader.readLine().split(" ");

            if (input.length == 2) {
                stack.push(parseInt(input[1]));
                continue;
            }

            String command = input[0];

            if (command.equals("pop")) {
                if (!stack.isEmpty()) {
                    System.out.println(stack.pop());
                } else {
                    System.out.println("-1");
                }
            } else if (command.equals("size")) {
                System.out.println(stack.size());

            } else if (command.equals("empty")) {
                int empty = stack.isEmpty() ? 1 : 0;
                System.out.println(empty);

            } else if (command.equals("top")) {
                if (!stack.isEmpty()) {
                    System.out.println(stack.peek());
                } else {
                    System.out.println("-1");
                }
            }
        }
    }
}
