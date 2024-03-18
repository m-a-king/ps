package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1504 {

    // 간선을 정의하는 클래스
    private static class Edge {
        int destination, weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    // 현재 상태를 저장하는 클래스
    private static class State implements Comparable<State> {
        int pos, accumulatedWeight;

        public State(int pos, int accumulatedWeight) {
            this.pos = pos;
            this.accumulatedWeight = accumulatedWeight;
        }

        // 우선순위 큐에서 사용하기 위한 오버라이딩
        @Override
        public int compareTo(State o) {

            // 자신과 입력받은 o를 비교
            return Integer.compare(this.accumulatedWeight, o.accumulatedWeight);
        }
    }

    // 인접 간선들을 저장하는 리스트
    static List<List<Edge>> adjList;
    static int n;

    // 최대 간선 개수 * 최대 간선 길이
    static int INF = 200_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        int e = Integer.parseInt(stringTokenizer.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(i, new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());

            // 각 노드에 간선을 추가 (양방향)
            adjList.get(a).add(new Edge(b, c));
            adjList.get(b).add(new Edge(a, c));
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        // 반드시 거쳐야 하는 정점들
        int mustPassV1 = Integer.parseInt(stringTokenizer.nextToken());
        int mustPassV2 = Integer.parseInt(stringTokenizer.nextToken());

        // 반드시 거쳐야 하는 정점들 사이의 거리
        int distBetweenMustPassVs = dijkstra(mustPassV1, mustPassV2);

        // 시작지점부터 반드시 거쳐야 하는 정점들까지의 거리
        int distFromStartToMustPassV1 = dijkstra(1, mustPassV1);
        int distFromStartToMustPassV2 = dijkstra(1, mustPassV2);

        // 반드시 거쳐야 하는 정점들로부터 목적 지점까지의 거리
        int distFromMustPassV1ToTarget = dijkstra(mustPassV1, n);
        int distFromMustPassV2ToTarget = dijkstra(mustPassV2, n);

        // 시작지점 -> 반드시 거쳐야하는 정점1 -> 반드시 거쳐야하는 정점2 -> 목표지점
        // 시작지점 -> 반드시 거쳐야하는 정점2 -> 반드시 거쳐야하는 정점1 -> 목표지점
        // 두 가지 루트 중에서 더 작은것을 출력
        int min = Math.min(
                distFromStartToMustPassV1 + distBetweenMustPassVs + distFromMustPassV2ToTarget,
                distFromStartToMustPassV2 + distBetweenMustPassVs + distFromMustPassV1ToTarget
        );

        // 혹시 최단거리가 이 문제에서 나올 수 있는 거리보다 길다면?
        // 해당 루트가 옳지 않다는 것 -> -1 출력
        if (min >= INF) {
            System.out.println(-1);
            return;
        }

        // 결과 츌력
        System.out.println(min);

    }


    // 다익스트라 알고리즘 메서드
    private static int dijkstra(int start, int target) {
        PriorityQueue<State> pq = new PriorityQueue<>();

        // 시작지점에서부터의 거리를 저장하는 배열
        int[] distances = new int[n + 1];

        // 배열 요소들을 최대값으로 초기화
        Arrays.fill(distances, INF);

        // 시작지점을 삽입
        pq.offer(new State(start, 0));
        distances[start] = 0;

        while (!pq.isEmpty()) {
            State current = pq.poll();

            // 종료조건
            if (current.pos == target) {
                return current.accumulatedWeight;
            }

            List<Edge> edges = adjList.get(current.pos);
            for (Edge edge : edges) {
                int nextWeight = current.accumulatedWeight + edge.weight;

                // 거리배열에 저장된 가중치(거리)보다 현재 계산하는 가중치(거리)가 작다면 덮어쓰기
                if (distances[edge.destination] > nextWeight) {
                    distances[edge.destination] = nextWeight;
                    State next = new State(edge.destination, nextWeight);
                    pq.offer(next);
                }
            }
        }

        // 종료조건에 부합하지 못했다면
        // 즉, 목표지점으로 가는 길이 없다면
        // 초기값(INF)을 반환
        return distances[target];
    }
}
