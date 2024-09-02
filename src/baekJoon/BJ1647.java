package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1647 {

    private static class Edge {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static int n;
    static int m;

    static List<List<Edge>> graph;
    static PriorityQueue<Edge> pq;
    static int[] parents;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        if (n == 2) {
            System.out.println(0);
            return;
        }

        graph = new ArrayList<>();
        pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        parents = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            parents[i] = i;
        }

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());

            Edge edge = new Edge(a, b, c);

            graph.get(a).add(edge);
            graph.get(b).add(edge);
            pq.offer(edge);
        }

        int count = 0;
        int res = 0;

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (find(curr.from) == find(curr.to)) continue;
            union(curr.from, curr.to);
            res += curr.weight;
            if (++count == n-2) {
                break;
            }
        }

        System.out.println(res);


    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }

        return find(parents[x]);
    }

    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX < rootY) {
            parents[rootY] = rootX;
        } else {
            parents[rootX] = rootY;
        }
    }
}
