package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ1927 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(bufferedReader.readLine());

            if (x == 0) {
                if (priorityQueue.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(priorityQueue.poll());
                }
            } else {
                priorityQueue.offer(x);
            }
        }
    }

}
