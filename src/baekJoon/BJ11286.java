package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BJ11286 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (Math.abs(o1) == Math.abs(o2)) {
                    return Integer.compare(o1, o2);
                }
                return Integer.compare(Math.abs(o1), Math.abs(o2));
            }
        });

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(bufferedReader.readLine());

            if (x == 0) {
                if (priorityQueue.isEmpty()) {
                    stringBuilder.append(0).append("\n");
                } else {
                    stringBuilder.append(priorityQueue.poll()).append("\n");
                }
            } else {
                priorityQueue.offer(x);
            }
        }

        System.out.println(stringBuilder);
    }
}
