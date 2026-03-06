package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
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
            boolean isBipartiteGraph = false;
            for (int j = 1; j <= V; j++) {

                if (visited[j]) {
                    continue;
                }

                if (isBipartiteGraph(j, visited, graph)) {
                    isBipartiteGraph = true;
                } else {
                    isBipartiteGraph = false;
                    break;
                }
            }
            answer.append(isBipartiteGraph ? "YES\n" : "NO\n");
        }

        System.out.println(answer);
    }

    private static boolean isBipartiteGraph(int startV, boolean[] visited, List<List<Integer>> graph) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] teams = new int[V + 1];
        queue.offer(startV);
        visited[startV] = true;
        teams[startV] = 1;

        while (!queue.isEmpty()) {
            final int cv = queue.poll();

            for (int nv : graph.get(cv)) {
                if (teams[nv] == teams[cv]) {
                    return false;
                }

                if (visited[nv]) {
                    continue;
                }
                visited[nv] = true;

                teams[nv] = teams[cv] == 1 ? 2 : 1;
                queue.offer(nv);
            }
        }

        return true;
    }
}
