package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2644 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int start = Integer.parseInt(stringTokenizer.nextToken());
        int end = Integer.parseInt(stringTokenizer.nextToken());

        int m = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int first = Integer.parseInt(stringTokenizer.nextToken());
            int second = Integer.parseInt(stringTokenizer.nextToken());

            adjList.get(first).add(second);
            adjList.get(second).add(first);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        int[] distance = new int[n + 1]; // 각 노드까지의 거리를 저장할 배열 추가

        queue.offer(start);
        visited[start] = true;


        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == end) {
                System.out.println(distance[end]);
                return;
            }

            List<Integer> adj = adjList.get(current);

            for (Integer a : adj) {
                if (!visited[a]) {
                    visited[a] = true;
                    queue.offer(a);
                    distance[a] = distance[current] + 1;
                }
            }
        }

        System.out.println(-1);
    }
}
