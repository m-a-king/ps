package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import static java.lang.Integer.parseInt;

public class BJ10773 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());

        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            int number = parseInt(bufferedReader.readLine());

            if (number == 0) {
                int pop = stack.pop();
                res -= pop;
                continue;
            }

            stack.push(number);
            res += number;
        }

        System.out.println(res);

    }
}
