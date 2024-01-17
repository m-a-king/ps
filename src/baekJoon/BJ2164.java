package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BJ2164 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            queue.offer(i + 1);
        }

        int last = 0;
        while (!queue.isEmpty()) {
            last = queue.poll();
            if (!queue.isEmpty()) {

                last = queue.poll();
                queue.offer(last);
            }
        }

        System.out.println(last);
    }
}
