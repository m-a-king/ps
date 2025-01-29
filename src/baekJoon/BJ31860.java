package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ31860 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder result = new StringBuilder();

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        PriorityQueue<Integer> importance = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < N; i++) {
            importance.offer(Integer.parseInt(bufferedReader.readLine()));
        }

        int yesterdaySatisfaction = 0;

        int count = 0;

        while (!importance.isEmpty()) {
            Integer currImportance = importance.poll();

            yesterdaySatisfaction = yesterdaySatisfaction / 2 + currImportance;
            count++;
            result.append(yesterdaySatisfaction).append("\n");

            int processedImportance = currImportance - M;
            if (processedImportance > K) {
                importance.offer(processedImportance);
            }
        }

        System.out.println(count);
        System.out.println(result);
    }
}
