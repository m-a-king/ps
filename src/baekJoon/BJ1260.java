package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1260 {
    static List<List<Integer>> adjList = new ArrayList<>();
    static StringBuilder stringBuilder = new StringBuilder();
    static int n, m;
    static boolean[] visited;



    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        int start = Integer.parseInt(stringTokenizer.nextToken());

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        for (List<Integer> adj : adjList) {
            adj.sort(Comparator.naturalOrder());
        }

        visited = new boolean[n + 1];
        dfs(start);

        stringBuilder.append("\n");

        visited = new boolean[n + 1];
        bfs(start);

        System.out.println(stringBuilder);
    }

    private static void dfs(int curr) {
        visited[curr] = true;
        stringBuilder.append(curr).append(" ");

        for (int next : adjList.get(curr)) {
            if (visited[next]) continue;
            visited[next] = true;
            dfs(next);
        }
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        stringBuilder.append(start).append(" ");
        visited[start] = true;

        while (!queue.isEmpty()) {
            Integer curr = queue.poll();
            for (int next : adjList.get(curr)) {
                if (visited[next]) continue;
                visited[next] = true;
                queue.offer(next);
                stringBuilder.append(next).append(" ");
            }
        }
    }


}
