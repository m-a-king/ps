package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ31932 {

    // 간선
    // 도착지, 거리, 그리고 다리가 무너지는 시간
    private static class Edge {
        int to;
        int dist, breakTime;

        public Edge(int to, int dist, int breakTime) {
            this.to = to;
            this.dist = dist;
            this.breakTime = breakTime;
        }
    }

    // 노드
    // 현재 위치, 도달 가능한 데드라인(deadline)
    private static class Node implements Comparable<Node> {
        int idx;
        int deadLine;

        public Node(int idx, int deadLine) {
            this.idx = idx;
            this.deadLine = deadLine;
        }

        @Override
        public int compareTo(Node other) {
            // 데드라인이 큰 노드를 우선 처리하기 위해 내림차순으로 정렬
            return other.deadLine - this.deadLine;
        }
    }

    static int n, m;
    static List<List<Edge>> graph;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken()); // 노드의 수
        m = Integer.parseInt(stringTokenizer.nextToken()); // 간선의 수

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 입력
        for (int i = 1; i <= m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());
            int t = Integer.parseInt(stringTokenizer.nextToken());

            if (d > t) continue; // 거리가 다리가 무너지기 전까지 도달할 수 없는 경우

            // 무방향 그래프이므로 양방향으로 간선 추가
            graph.get(u).add(new Edge(v, d, t));
            graph.get(v).add(new Edge(u, d, t));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] bestDeadLine = new int[n + 1]; // 각 노드에 대해 도달 가능한 최적의 데드라인 (클수록 좋음)
        Arrays.fill(bestDeadLine, -1); // 모든 노드의 데드라인을 무효한 값으로 초기화

        // 도착 지점(n번 노드, 사실상 출발 지점)의 인접 노드에 대해 초기화
        for (Edge edge : graph.get(n)) {
            pq.offer(new Node(edge.to, edge.breakTime - edge.dist));
            bestDeadLine[n] = Math.max(bestDeadLine[n], edge.breakTime); // 도착 지점에서의 데드라인 갱신
            bestDeadLine[edge.to] = edge.breakTime - edge.dist; // 인접 노드의 초기 데드라인 설정
        }

        // 다익스트라 알고리즘
        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            // 기록된 데드라인이 더 크다면 현재 노드 무시
            if(curr.deadLine < bestDeadLine[curr.idx]) continue;
            bestDeadLine[curr.idx] = curr.deadLine;

            // 목표 지점(1번 노드)에 도달한 경우
            if (curr.idx == 1) {
                System.out.println(curr.deadLine);
                return;
            }

            // 인접한 노드들을 탐색하며 데드라인 갱신
            for (Edge adjEdge : graph.get(curr.idx)) {
                // 다음 노드의 데드라인 계산
                // 1. 현재 노드의 데드라인 - 간선의 거리
                // 2. 간선이 무너지는 시간 - 간선의 거리
                // 둘 중에서 작은 값이 유효한 다음 노드의 데드라인
                int nextNodeDeadLine = Math.min(curr.deadLine, adjEdge.breakTime) - adjEdge.dist;

                // 도달할 수 없거나 이미 더 좋은 데드라인이 있는 경우 무시
                if (nextNodeDeadLine <= bestDeadLine[adjEdge.to]) continue;

                bestDeadLine[adjEdge.to] = nextNodeDeadLine; // 데드라인 갱신
                pq.offer(new Node(adjEdge.to, nextNodeDeadLine)); // 다음 노드를 큐에 추가
            }
        }

        // 목표 지점에 도달할 수 없는 경우
        System.out.println(-1);
    }
}