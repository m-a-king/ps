package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1167 {

    private static class Edge {
        int target, distance;

        public Edge(int target, int distance) {
            this.target = target;
            this.distance = distance;
        }
    }

    private static class NodeState {
        int currentPosition; // 현재 노드의 위치
        int accumulatedDistance; // 시작 지점으로부터 누적된 거리

        public NodeState(int currentPosition, int accumulatedDistance) {
            this.currentPosition = currentPosition;
            this.accumulatedDistance = accumulatedDistance;
        }
    }

    static int n;
    static List<List<Edge>> adjList;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());

        adjList = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            int current = Integer.parseInt(stringTokenizer.nextToken());

            while (true) {
                int node = Integer.parseInt(stringTokenizer.nextToken());
                if (node == -1) {
                    break;
                }
                int dist = Integer.parseInt(stringTokenizer.nextToken());
                adjList.get(current).add(new Edge(node, dist));
            }
        }




        NodeState foundNode = bfs(1);
        NodeState resNode = bfs(foundNode.currentPosition);
        System.out.println(resNode.accumulatedDistance);



    }

    private static NodeState bfs(int startNode) {
        Queue<NodeState> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        queue.offer(new NodeState(startNode, 0));
        visited[startNode] = true;

        NodeState farthestNode = new NodeState(-1, -1);

        while (!queue.isEmpty()) {
            NodeState current = queue.poll();

            List<Edge> edges = adjList.get(current.currentPosition);
            for (Edge edge : edges) {
                if (!visited[edge.target]) {
                    visited[edge.target] = true;
                    NodeState nextNode = new NodeState(edge.target, current.accumulatedDistance + edge.distance);
                    queue.offer(nextNode);
                    if (farthestNode.accumulatedDistance < nextNode.accumulatedDistance) {
                        farthestNode = nextNode;
                    }
                }
            }
        }

        return farthestNode;
    }
}

