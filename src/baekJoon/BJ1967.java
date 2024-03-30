package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1967 {

    private static class Edge {
        int target, weight;

        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    private static class State {
        int nowPos, accDist;

        public State(int nowPos, int accDist) {
            this.nowPos = nowPos;
            this.accDist = accDist;
        }
    }

    static List<List<Edge>> adjList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        if (n == 1) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            int weight = Integer.parseInt(stringTokenizer.nextToken());

            // 최대 노드 번호를 갱신하는 로직은 제거하였으며, 이는 앞서 모든 노드를 위해 리스트를 초기화했기 때문입니다.
            adjList.get(start).add(new Edge(end, weight));
            adjList.get(end).add(new Edge(start, weight));
        }

        State farthestNode = bfs(1);


        State answer = bfs(farthestNode.nowPos);
        System.out.println(answer.accDist);
    }

    private static State bfs(int start) {
        Queue<State> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[adjList.size()];

        queue.offer(new State(start, 0));
        visited[start] = true;

        State farthestNode = new State(-1, -1);

        while (!queue.isEmpty()) {
            State nowState = queue.poll();

            List<Edge> adjEdges = adjList.get(nowState.nowPos);

            for (Edge edge : adjEdges) {
                if (!visited[edge.target]) {
                    visited[edge.target] = true;
                    int nextAccDist = nowState.accDist + edge.weight;
                    queue.offer(new State(edge.target, nextAccDist));

                    if (farthestNode.accDist < nextAccDist) {
                        farthestNode = new State(edge.target, nextAccDist);
                    }
                }
            }
        }

        return farthestNode;
    }
}
