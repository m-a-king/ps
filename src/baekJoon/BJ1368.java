package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1368 {

    public static final int WELL = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparing(edge -> edge.weight));

        for (int i = 1; i <= N; i++) {
            int weight = Integer.parseInt(bufferedReader.readLine());
            edges.offer(new Edge(WELL, i, weight));
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= i; j++) {
                int weight = Integer.parseInt(stringTokenizer.nextToken());
                edges.offer(new Edge(i, j, weight));
            }
        }

        int result = 0;
        int selectCount = 0;

        int[] parents = new int[N + 1];
        Arrays.fill(parents, -1);

        while (selectCount < N) {
            Edge edge = edges.poll();

            int parent1 = find(parents, edge.from);
            int parent2 = find(parents, edge.to);

            if (parent1 == parent2) {
                continue;
            }

            union(parent1, parent2, parents);

            result += edge.weight;
            selectCount++;
        }

        System.out.println(result);
    }

    private static int find(int[] parents, int target) {
        if (parents[target] < 0) {
            return target;
        }
        return parents[target] = find(parents, parents[target]);
    }

    private static void union(int parent1, int parent2, int[] parents) {
        if (parents[parent1] < parents[parent2]) {
            parents[parent1] += parents[parent2];
            parents[parent2] = parent1;
            return;
        }
        parents[parent2] += parents[parent1];
        parents[parent1] = parent2;
    }

    private static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}

