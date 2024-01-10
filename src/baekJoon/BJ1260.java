package baekJoon;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BJ1260 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int nVertex = input[0];
        int nEdge = input[1];
        int start = input[2];

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < nEdge; i++) {
            String[] input2 = bufferedReader.readLine().split(" ");
            int s = parseInt(input2[0]);
            int e = parseInt(input2[1]);

            List<Integer> list = graph.getOrDefault(s, new ArrayList<>());
            list.add(e);
            graph.put(s, list);

            list = graph.getOrDefault(e, new ArrayList<>());
            list.add(s);
            graph.put(e, list);
        }

        for (int vertex : graph.keySet()) {
            Collections.sort(graph.get(vertex));
        }

        boolean[] visited = new boolean[nVertex + 1];

        dfs(graph, start, visited);
        System.out.println();
        Arrays.fill(visited, false);

        bfs(graph, start, visited);

    }

    private static void bfs(Map<Integer, List<Integer>> graph, int vertex, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(vertex);
        visited[vertex] = true;

        while (!queue.isEmpty()) {
            Integer now = queue.poll();
            System.out.print(now + " ");
            List<Integer> neighbors = graph.getOrDefault(now, new ArrayList<>());

            for (Integer neighbor : neighbors) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    private static void dfs(Map<Integer, List<Integer>> graph, int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        List<Integer> neighbors = graph.getOrDefault(vertex, new ArrayList<>());
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited);
            }
        }
    }
}
