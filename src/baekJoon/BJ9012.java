package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import static java.lang.Integer.parseInt;

public class BJ9012 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());

        for (int i = 0; i < n; i++) {

            String[] input = bufferedReader.readLine().split("");

            Stack<Integer> stack = new Stack<>();

            int flag = 0;
            for (String s : input) {
                if (s.equals("(")) {
                    stack.push(0);
                } else if (s.equals(")")) {
                    if (stack.isEmpty()) {
                        flag = 1;
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }

            if (flag == 1 || !stack.isEmpty()) {
                System.out.println("NO");

            } else {
                System.out.println("YES");
            }
        }
    }
}
