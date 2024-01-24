package programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class 네트워크 {
    public int solution(int n, int[][] computers) {
        int answer = 0;

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (computers[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {
                queue.offer(i);
                visited[i] = true;
                bfs(queue, graph, visited);
                answer++;
            }
        }
        return answer;
    }

    private void bfs(Queue<Integer> queue, List<List<Integer>> graph, boolean[] visited) {
        while (!queue.isEmpty()) {
            int current = queue.poll();

            List<Integer> nextNodes = graph.get(current);
            for (int next : nextNodes) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }
}