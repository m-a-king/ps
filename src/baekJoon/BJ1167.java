package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1167 {

    // 간선을 정의하는 클래스
    private static class Edge {
        int target, distance;

        public Edge(int target, int distance) {
            this.target = target;
            this.distance = distance;
        }
    }

    // 노드의 상태를 정의하는 클래스
    private static class NodeState {

        // 현재 노드의 위치
        int currentPosition;

        // 시작 지점으로부터 누적된 거리
        int accumulatedDistance;

        public NodeState(int currentPosition, int accumulatedDistance) {
            this.currentPosition = currentPosition;
            this.accumulatedDistance = accumulatedDistance;
        }
    }

    // 입력되는 노드의 개수
    static int n;

    // 노드들의 간선들을 저장하는 리스트
    // 노드의 개수가 주어지므로 배열안에 리스트를 선언해도 무방함
    static List<List<Edge>> adjList;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());

        adjList = new ArrayList<>();

        // 인접 리스트 초기화
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        // 인접 리스트 채우기
        for (int i = 1; i <= n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            // 특정 노드
            int current = Integer.parseInt(stringTokenizer.nextToken());

            while (true) {

                // 타겟 노드
                int node = Integer.parseInt(stringTokenizer.nextToken());

                // 종료 조건
                if (node == -1) {
                    break;
                }

                // 타겟 노드까지의 거리
                int dist = Integer.parseInt(stringTokenizer.nextToken());

                // 인접 리스트의 특정 노드에 타겟 노드까지의 간선 정보를 추가
                adjList.get(current).add(new Edge(node, dist));
            }
        }

        // 임의의 노드(1)로 부터 가장 먼 노드를 찾기
        NodeState foundNode = bfs(1);

        // 위에서 찾은 노드에서부터 가장 먼 노드를 찾기 -> 트리 지름
        NodeState resNode = bfs(foundNode.currentPosition);

        // 트리 지름 출력
        System.out.println(resNode.accumulatedDistance);

    }

    // 출발 노드를 받고 bfs 알고리즘을 진행하는 메서드
    private static NodeState bfs(int startNode) {

        Queue<NodeState> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];

        // 큐에 출발 노드를 삽입, 방문 처리
        queue.offer(new NodeState(startNode, 0));
        visited[startNode] = true;

        // 가장 먼 노드를 저장할 객체 초기화
        NodeState farthestNode = new NodeState(-1, -1);

        while (!queue.isEmpty()) {
            NodeState current = queue.poll();

            // 큐에서 꺼낸 노드의 인접한 간선들을 추출
            List<Edge> edges = adjList.get(current.currentPosition);

            for (Edge edge : edges) {

                // 방문하지 않은 노드라면
                if (!visited[edge.target]) {
                    visited[edge.target] = true;

                    // 해당 간선을 지나간 노드의 상태를 선언하고 큐에 추가
                    NodeState nextNode = new NodeState(edge.target, current.accumulatedDistance + edge.distance);
                    queue.offer(nextNode);

                    // 출발 노드에서부터 가장 먼 노드로 설정한 노드보다
                    // 방금 계산한 노드가 더 멀다면 덮어씌움
                    if (farthestNode.accumulatedDistance < nextNode.accumulatedDistance) {
                        farthestNode = nextNode;
                    }
                }
            }
        }

        // 큐가 비었다면 bfs 종료
        // 가장 먼 노드를 반환함
        return farthestNode;
    }
}