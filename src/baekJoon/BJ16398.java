package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ16398 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge -> Edge.weight));

        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            for (int j = 0; j < N; j++) {
                if (i < j) {
                    continue;
                }
                int weight = Integer.parseInt(stringTokenizer.nextToken());
                pq.offer(new Edge(i, j, weight));
            }
        }

        int selectCount = 0;
        int totalWeight = 0;

        int[] parents = new int[N];
        Arrays.fill(parents, -1);


        while (selectCount < N - 1) {
            Edge edge = pq.poll();

            assert edge != null;
            int parent1 = find(parents, edge.x);
            int parent2 = find(parents, edge.y);

            if (parent1 == parent2) {
                continue;
            }

            union(parents, parent1, parent2);

            totalWeight += edge.weight;
            selectCount++;
        }

        System.out.println(totalWeight);

    }

    private static void union(int[] parents, int parent1, int parent2) {
        if (parents[parent1] < parents[parent2]) {
            parents[parent1] += parents[parent2];
            parents[parent2] = parent1;
            return;
        }
        parents[parent2] += parents[parent1];
        parents[parent1] = parent2;
    }

    private static int find(int[] parents, int target) {
        if (parents[target] < 0) {
            return target;
        }
        return parents[target] = find(parents, parents[target]);
    }

    private static class Edge {
        final int x;
        final int y;
        final int weight;

        public Edge(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }
}
