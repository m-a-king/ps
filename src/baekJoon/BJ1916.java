package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Math.min;

public class BJ1916 {

    private static class Bus implements Comparable<Bus>{
        int end, cost;

        public Bus(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int cityCount = parseInt(bufferedReader.readLine());
        int busCount = parseInt(bufferedReader.readLine());
        List<List<Bus>> graph = new ArrayList<>();
        for (int i = 0; i <= cityCount; i++) {
            graph.add(i, new ArrayList<>());
        }

        for (int bus = 0; bus < busCount; bus++) {
            int[] busInfo = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int busStart = busInfo[0];
            int busEnd = busInfo[1];
            int busCost = busInfo[2];

            List<Bus> buses = graph.get(busStart);
            buses.add(new Bus(busEnd, busCost));
        }

        int[] se = (Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        int startPoint = se[0];
        int endPoint = se[1];

        int[] costs = new int[cityCount + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[startPoint] = 0;
        PriorityQueue<Bus> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Bus(startPoint, 0));

        while (!priorityQueue.isEmpty()) {
            Bus current = priorityQueue.poll();

            if (current.end == endPoint) {
                break;
            }

            if (costs[current.end] < current.cost) {
                continue;
            }

            for (Bus nextB : graph.get(current.end)) {
                if (costs[nextB.end] > costs[current.end] + nextB.cost) {
                    costs[nextB.end] = costs[current.end] + nextB.cost;
                    priorityQueue.offer(new Bus(nextB.end, costs[nextB.end]));
                }
            }


        }

        System.out.println(costs[endPoint]);




    }
}
