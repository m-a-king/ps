package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BJ11866 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");

        int length = parseInt(s[0]);
        int del = parseInt(s[1]);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<");

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= length; i++) {
            queue.offer(i);
        }

        while (!queue.isEmpty()) {
            for (int i = 0; i < del-1; i++) {
                queue.offer(queue.poll());
            }
            stringBuilder.append(queue.poll());
            stringBuilder.append(", ");
        }


        stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());
        stringBuilder.append(">");

        System.out.println(stringBuilder);

    }
}
