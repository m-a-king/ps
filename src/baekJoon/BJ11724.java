package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ11724 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            adjList.get(u).add(v);
            adjList.get(v).add(u); // 양방향 그래프 처리
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                queue.offer(i);
                visited[i] = true;
                count++;
            }

            while (!queue.isEmpty()) {

                Integer current = queue.poll();
                List<Integer> list = adjList.get(current);

                for (int vertex : list) {
                    if (!visited[vertex]) {
                        queue.offer(vertex);
                        visited[vertex] = true;
                    }
                }
            }
        }

        System.out.println(count);

    }

}
