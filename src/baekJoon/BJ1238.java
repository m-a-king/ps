package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

public class BJ1238 {

    static List<List<Node>> graph = new ArrayList<>();

    static class Node implements Comparable<Node> {

        int idx, time;

        public Node(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    static int studentsCount;
    static int roadsCount;
    static int partyStudent;
    static int[][] timeMap;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");

        studentsCount = parseInt(s[0]);
        roadsCount = parseInt(s[1]);
        partyStudent = parseInt(s[2]);

        for (int i = 0; i <= studentsCount; i++) {
            graph.add(new ArrayList<Node>());
        }

        for (int i = 0; i < roadsCount; i++) {
            int[] roadInfo = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph.get(roadInfo[0]).add(new Node(roadInfo[1], roadInfo[2]));
        }

        timeMap = new int[studentsCount + 1][studentsCount + 1];

        for (int i = 1; i <= studentsCount; i++) {
            dijkstra(i);
        }

        int maxTime = 0;

        for (int i = 1; i <= studentsCount; i++) {
            if (i == partyStudent) {
                continue;
            }
            int totalTime = timeMap[i][partyStudent] + timeMap[partyStudent][i];
            maxTime = max(totalTime, maxTime);
        }

        System.out.println(maxTime);

    }


    public static void dijkstra(int start) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        int[] times = new int[studentsCount + 1];
        Arrays.fill(times, Integer.MAX_VALUE);
        times[start] = 0;
        boolean[] visited = new boolean[studentsCount + 1];

        priorityQueue.offer(new Node(start, 0));
        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();
            if (visited[current.idx]) {
                continue;
            }

            if (times[current.idx] < current.time) {
                continue;
            }

            for (Node next : graph.get(current.idx)) {
                int nextIdx = next.idx;
                int newTime = times[current.idx] + next.time;

                if (newTime < times[nextIdx]) {
                    times[nextIdx] = newTime;
                    priorityQueue.offer(new Node(nextIdx, newTime));
                }
            }
        }

        timeMap[start] = times;
    }


}
