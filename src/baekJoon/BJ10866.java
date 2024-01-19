package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

import static java.lang.Integer.parseInt;

public class BJ10866 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            String[] input = bufferedReader.readLine().split(" ");

            if (input.length == 2) {
                if (input[0].equals("push_back")) {

                    deque.offerLast(parseInt(input[1]));
                } else {
                    deque.offerFirst(parseInt(input[1]));
                }
            } else {
                String command = input[0];
                boolean isEmpty = deque.isEmpty();
                int output = 0;

                if (command.equals("pop_front")) {
                    output = (isEmpty) ? -1 : deque.pollFirst();
                } else if (command.equals("pop_back")) {
                    output = (isEmpty) ? -1 : deque.pollLast();
                } else if (command.equals("size")) {
                    output = deque.size();
                } else if (command.equals("empty")) {
                    output = isEmpty ? 1 : 0;
                } else if (command.equals("front")) {
                    output = isEmpty ? -1 : deque.peekFirst();
                } else if (command.equals("back")) {
                    output = isEmpty ? -1 : deque.peekLast();
                }

                System.out.println(output);
            }
        }
    }
}
