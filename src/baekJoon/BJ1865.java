package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1865 {

    private static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        while (t-- > 0) {

            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int w = Integer.parseInt(stringTokenizer.nextToken());

            List<List<Edge>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                int from = Integer.parseInt(stringTokenizer.nextToken());
                int to = Integer.parseInt(stringTokenizer.nextToken());
                int weight = Integer.parseInt(stringTokenizer.nextToken());

                graph.get(from).add(new Edge(to, weight));
                graph.get(to).add(new Edge(from, weight));
            }

            for (int i = 0; i < w; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                int from = Integer.parseInt(stringTokenizer.nextToken());
                int to = Integer.parseInt(stringTokenizer.nextToken());
                int weight = -Integer.parseInt(stringTokenizer.nextToken());

                graph.get(from).add(new Edge(to, weight));
            }

            if (findNC(n, graph, stringBuilder)) {
                stringBuilder.append("YES").append("\n");
            } else {
                stringBuilder.append("NO").append("\n");
            }

        }

        System.out.println(stringBuilder);
    }

    private static boolean findNC(int n, List<List<Edge>> graph, StringBuilder stringBuilder) {
        // 가중치 0미만의 사이클 탐색
        int start = 1;

        int[] distance = new int[n + 1];
        Arrays.fill(distance, 2500*10000);
        distance[start] = 0;

        for (int i = 1; i < n; i++) {

            for (int from = 1; from <= n; from++) {
                for (Edge edge : graph.get(from)) {

                    distance[edge.to] = Math.min(distance[edge.to], distance[from] + edge.weight);
                }
            }

        }

        for (int from = 1; from <= n; from++) {
            for (Edge edge : graph.get(from)) {
                if (distance[edge.to] > distance[from] + edge.weight) {
                    return true;
                }
            }
        }

        return false;
    }

}

