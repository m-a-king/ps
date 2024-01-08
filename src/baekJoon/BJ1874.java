package baekJoon;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BJ1874 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = parseInt(bufferedReader.readLine());
        int[] target = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            target[i] = parseInt(bufferedReader.readLine());
        }

        int number = 0;
        int i = 0;



        while (i != n) {
            int peek = (stack.peek() == null) ? -1 : stack.peek();

            if (peek == -1) {
                stack.push(number + 1);
                number++;
                result.add("+");

                continue;
            }

            if (peek == target[i]) {
                stack.pop();
                result.add("-");

                i++;
            } else if (peek < target[i]) {
                stack.push(number + 1);
                result.add("+");
                number++;
            } else {
                System.out.println("NO");
                return;
            }
        }

        for (String res : result
        ) {
            System.out.println(res);

        }


    }
}
