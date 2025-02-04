package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ2150 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int V = Integer.parseInt(stringTokenizer.nextToken());
        int E = Integer.parseInt(stringTokenizer.nextToken());

        List<List<Integer>> outGraph = new ArrayList<>();
        List<List<Integer>> inGraph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            outGraph.add(new ArrayList<>());
            inGraph.add(new ArrayList<>());
        }

        while (E-- > 0) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int A = Integer.parseInt(stringTokenizer.nextToken());
            int B = Integer.parseInt(stringTokenizer.nextToken());

            outGraph.get(A).add(B);
            inGraph.get(B).add(A);
        }

        boolean[] visited = new boolean[V + 1];
        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i <= V; i++) {
            dfs(visited, stack, outGraph, i);
        }

        visited = new boolean[V + 1];
        List<List<Integer>> result = new ArrayList<>();

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            List<Integer> SCC = new ArrayList<>();
            dfs(visited, SCC, inGraph, curr);
            SCC.sort(Comparator.naturalOrder());
            if (!SCC.isEmpty()) {
                result.add(SCC);
            }
        }

        result.sort((a, b) -> a.get(0) - b.get(0));

        StringBuilder stringBuilder = new StringBuilder();

        result.forEach(SCC ->
                {
                    SCC.forEach(v -> stringBuilder.append(v).append(" "));
                    stringBuilder.append("-1").append("\n");
                }
        );

        System.out.println(result.size());
        System.out.println(stringBuilder);

    }

    private static void dfs(boolean[] visited, Stack<Integer> stack, List<List<Integer>> graph, int i) {
        if (visited[i]) {
            return;
        }
        visited[i] = true;

        for (int next : graph.get(i)) {
            dfs(visited, stack, graph, next);
        }

        stack.push(i);
    }

    private static void dfs(boolean[] visited, List<Integer> SCC, List<List<Integer>> graph, int i) {
        if (visited[i]) {
            return;
        }
        visited[i] = true;
        SCC.add(i);

        for (int next : graph.get(i)) {
            dfs(visited, SCC, graph, next);
        }
    }
}
