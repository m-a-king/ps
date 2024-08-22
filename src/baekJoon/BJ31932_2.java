package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ31932_2 {

    private static class Edge {
        int to, dist, breakTime;

        public Edge(int to, int dist, int breakTime) {
            this.to = to;
            this.dist = dist;
            this.breakTime = breakTime;
        }

    }

    private static class Mover implements Comparable<Mover> {
        int pos, time;

        public Mover(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }

        @Override
        public int compareTo(Mover o) {
            return this.time - o.time;
        }
    }


    static int n, m;
    static List<List<Edge>> graph;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());
            int t = Integer.parseInt(stringTokenizer.nextToken());

            graph.get(u).add(new Edge(v, d, t));
            graph.get(v).add(new Edge(u, d, t));
        }

        int maxFish = 0;

        int start = 0; // 최소 물고기
        int end = 0;// 최대 물고기

        for (Edge edge : graph.get(1)) {
            end = Math.max(end, edge.breakTime - edge.dist); // 최대 물고기
        }

        if (end == 0) {
            if (dijkstra(0)) {
                System.out.println(0);
            } else {
                System.out.println(-1);

            }
            return;
        }

        while (start <= end) {
            int mid = (start + end) / 2;
            if (maxFish > mid) {
                start = mid + 1;
                continue;
            }

            if (dijkstra(mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(end);

    }

    private static boolean dijkstra(int huntTime) {
        PriorityQueue<Mover> pq = new PriorityQueue<>();
        long[] bestRecord = new long[n + 1];  // 각 노드에 도달할 때의 최적 시간을 저장하는 배열
        Arrays.fill(bestRecord, Long.MAX_VALUE);  // 초기값

        pq.offer(new Mover(1, huntTime));
        bestRecord[1] = huntTime;  // 출발 지점에서의 초기 시간을 설정

        while (!pq.isEmpty()) {
            Mover curr = pq.poll();

            // 이미 최적의 시간으로 방문한 노드라면 스킵
            if (curr.time > bestRecord[curr.pos]) continue;

            // 목적지에 도달한 경우
            if (curr.pos == n) {
                return true;
            }

            for (Edge nextEdge : graph.get(curr.pos)) {
                long newTime = curr.time + nextEdge.dist;

                // 다리가 무너지기 전에 도착할 수 없는 경우 스킵
                if (nextEdge.breakTime < newTime) continue;

                // 더 최적의 시간으로 도달할 수 있는 경우에만 업데이트하고 큐에 넣음
                if (newTime < bestRecord[nextEdge.to]) {
                    bestRecord[nextEdge.to] = newTime;
                    pq.offer(new Mover(nextEdge.to, (int) newTime));
                }
            }
        }
        return false;
    }

}
