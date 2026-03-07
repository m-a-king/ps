package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1707 {

    static StringBuilder answer = new StringBuilder();
    static int V;
    static int E;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        final int K = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < K; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            V = Integer.parseInt(stringTokenizer.nextToken());
            E = Integer.parseInt(stringTokenizer.nextToken());

            List<List<Integer>> graph = new ArrayList<>();
            for (int j = 0; j <= V; j++) {
                graph.add(new ArrayList<>());
            }

            for (int j = 0; j < E; j++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                final int u = Integer.parseInt(stringTokenizer.nextToken());
                final int v = Integer.parseInt(stringTokenizer.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            boolean[] visited = new boolean[V + 1];
            int[] color = new int[V + 1];
            boolean bipartiteGraph = true;

            for (int j = 1; j <= V; j++) {
                if (visited[j]) {
                    continue;
                }
                color[j] = 1;

                bipartiteGraph = isBipartiteGraph(j, visited, graph, color);

                if (!bipartiteGraph) {
                    break;
                }
            }

            answer.append(bipartiteGraph ? "YES\n" : "NO\n");
        }
        System.out.println(answer);
    }

    private static boolean isBipartiteGraph(
            int cv,
            boolean[] visited,
            List<List<Integer>> graph,
            int[] color
    ) {
        visited[cv] = true;

        for (Integer nv : graph.get(cv)) {
            if (!visited[nv]) {
                color[nv] = color[cv] == 1 ? 2 : 1;
                if (!isBipartiteGraph(nv, visited, graph, color)) {
                    return false;
                }
            }
            if (color[nv] == color[cv]) {
                return false;
            }
        }

        return true;
    }
}
