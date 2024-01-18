package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BJ10845 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            String[] input = bufferedReader.readLine().split(" ");

            if (input.length == 2) {
                queue.offer(parseInt(input[1]));
                continue;
            }

            String command = input[0];

            if (command.equals("pop")) {
                if (!queue.isEmpty()) {
                        System.out.println(queue.poll());
                    } else {
                        System.out.println("-1");
                }
            } else if (command.equals("size")) {
                System.out.println(queue.size());
            } else if (command.equals("empty")) {
                int empty = queue.isEmpty() ? 1 : 0;
                System.out.println(empty);
            } else if (command.equals("front")) {
                if (!queue.isEmpty()) {
                    System.out.println(queue.peek());
                } else {
                    System.out.println("-1");
                }
            } else if (command.equals("back")) {
                if (!queue.isEmpty()) {
                    System.out.println(queue.peekLast());
                } else {
                    System.out.println("-1");
                }
            }

        }
    }
}
