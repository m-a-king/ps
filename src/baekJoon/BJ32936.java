package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ32936 {

    private static class State {
        int time;
        int idx;

        public State(int time, int idx) {
            this.time = time;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        int a = Integer.parseInt(stringTokenizer.nextToken());
        int b = Integer.parseInt(stringTokenizer.nextToken());
        int c = Integer.parseInt(stringTokenizer.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        while (M-- > 0) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());

            graph.get(u).add(v);
        }

        int[] visited = new int[N + 1];

        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(0, 1));

        while (!pq.isEmpty()) {
            State curr = pq.poll();

            if (curr.idx == a) {
                if (visited[curr.idx] > curr.time - c) {

                }
                pq.offer(new State(curr.time - c, b));
            }

            for (int nextIdx : graph.get(curr.idx)) {

                if (visited[nextIdx] < curr.time) {
                    continue;
                }
                visited[nextIdx] = curr.time;

                pq.offer(new State(curr.time + 1, nextIdx));
            }
        }
    }
}
