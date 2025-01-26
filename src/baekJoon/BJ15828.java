package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BJ15828 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        Queue<Integer> queue = new ArrayDeque<>();
        int size = 0;

        while (true) {
            int info = Integer.parseInt(bufferedReader.readLine());

            if (info == -1) {
                break;
            }

            if (info == 0) {
                queue.poll();
                size--;
                continue;
            }

            if (size == N) {
                continue;
            }

            queue.offer(info);
            size++;
        }

        if (size == 0) {
            System.out.println("empty");
            return;
        }

        StringBuilder result = new StringBuilder();

        for(int e : queue) {
            result.append(e).append(" ");
        }

        System.out.println(result.toString().trim());
    }
}
