package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1766 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        List<List<Integer>> preSolve = new ArrayList<>();
        int[] priorities = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            preSolve.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());

            preSolve.get(a).add(b); // b 퓰기 전에 a 풀어야 한다.
            priorities[b]++;
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            if (priorities[i] == 0) {
                priorityQueue.add(i);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            Integer curr = priorityQueue.poll();
            stringBuilder.append(curr).append(" ");

            for (int next : preSolve.get(curr)) {
                if (--priorities[next] == 0) {
                    priorityQueue.offer(next);
                }
            }
        }

        System.out.print(stringBuilder.toString().trim());
    }
}

//10 1