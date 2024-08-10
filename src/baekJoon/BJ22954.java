package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ22954 {

    private static class Edge {
        int to, idx;

        public Edge(int to, int idx) {
            this.to = to;
            this.idx = idx;
        }
    }

    static int n, m;
    static List<List<Edge>> graph;
    static boolean[] visited;
    static int dfsCount = 0;
    static List<Integer> edgePath;
    static List<Integer> nodePath;

    static StringBuilder stringBuilder = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        if (n <= 2) {
            System.out.println(-1);
            return;
        }

        visited = new boolean[n + 1];
        visited[0] = true;

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());

            graph.get(u).add(new Edge(v, i));
            graph.get(v).add(new Edge(u, i));
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            if (dfsCount == 2) {
                System.out.println(-1);
                return;
            }
            visited[i] = true;
            dfsCount++;
            edgePath = new ArrayList<>();
            nodePath = new ArrayList<>();
            nodePath.add(i);
            dfs(i);

            if (edgePath.size() == n - 1) {
                calc();
                break;
            }

            if (dfsCount == 1) {
                if (2 * nodePath.size() == n) {
                    System.out.println(-1);
                    return;
                }
                stringBuilder
                        .append(nodePath.size())
                        .append(" ")
                        .append(n - nodePath.size())
                        .append("\n");
            }

            for (int node : nodePath) {
                stringBuilder.append(node).append(" ");
            }
            stringBuilder.append("\n");

            for (int edge : edgePath) {
                stringBuilder.append(edge).append(" ");
            }
            stringBuilder.append("\n");


        }

        System.out.println(stringBuilder);

    }

    private static void calc() {
        stringBuilder.append(n - 1).append(" ").append(1);

        stringBuilder.append("\n");

        for (int i = 0; i < nodePath.size() - 1; i++) {
            stringBuilder.append(nodePath.get(i)).append(" ");
        }

        stringBuilder.append("\n");

        for (int i = 0; i < edgePath.size() - 1; i++) {
            stringBuilder.append(edgePath.get(i)).append(" ");
        }

        stringBuilder.append("\n");

        stringBuilder.append(nodePath.get(nodePath.size() - 1));

        stringBuilder.append("\n");
    }

    private static void dfs(int nodeIdx) {

        List<Edge> edges = graph.get(nodeIdx);
        for (Edge edge : edges) {
            if (visited[edge.to]) continue;
            visited[edge.to] = true;
            edgePath.add(edge.idx);
            nodePath.add(edge.to);

            dfs(edge.to);
        }

    }
}
