package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.*;

public class BJ1753 {

    static class Edge implements Comparable<Edge>{
        int dest, weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] VE = bufferedReader.readLine().split(" ");
        int V = parseInt(VE[0]);
        int E = parseInt(VE[1]);

        int start = parseInt(bufferedReader.readLine());

        List<List<Edge>> adjacentList = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            adjacentList.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            String[] uvw = bufferedReader.readLine().split(" ");
            int u = parseInt(uvw[0]);
            int v = parseInt(uvw[1]);
            int w = parseInt(uvw[2]);

            adjacentList.get(u).add(new Edge(v, w));
        }

        int[] minRes = new int[V + 1];
        Arrays.fill(minRes, MAX_VALUE);

        PriorityQueue<Edge> queue = new PriorityQueue<>();

        queue.offer(new Edge(start, 0));
        minRes[start] = 0;

        while (!queue.isEmpty()) {
            Edge current = queue.poll();
            int currentDest = current.dest;
            int currentWeight = current.weight;

            if (minRes[currentDest] < currentWeight) continue;

            for (Edge edge : adjacentList.get(currentDest)) {
                if (minRes[edge.dest] > currentWeight + edge.weight) {
                    minRes[edge.dest] = currentWeight + edge.weight;
                    queue.offer(new Edge(edge.dest, minRes[edge.dest]));
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (minRes[i] == MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(minRes[i]);
            }
        }
    }
}
