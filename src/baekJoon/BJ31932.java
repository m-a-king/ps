package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ31932 {

    private static class Edge {
        int to;
        long dist, breakTime;

        public Edge(int to, long dist, long breakTime) {
            this.to = to;
            this.dist = dist;
            this.breakTime = breakTime;
        }
    }

    private static class Reverser implements Comparable<Reverser> {
        int pos;
        long time;

        public Reverser(int pos, long time) {
            this.pos = pos;
            this.time = time;
        }

        @Override
        public int compareTo(Reverser other) {
            return Long.compare(other.time, this.time); // 큰 시간이 우선이 되도록 정렬
        }
    }

    static int n, m;
    static List<List<Edge>> graph;
    static long[] bestRecord;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        bestRecord = new long[n + 1];  // 도착지(출발하는 지점)에서 각 정점까지의 이동했을 때 가장 많이 남은 시간
        Arrays.fill(bestRecord, 0);

        for (int i = 1; i <= m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            long d = Long.parseLong(stringTokenizer.nextToken());
            long t = Long.parseLong(stringTokenizer.nextToken());

            graph.get(u).add(new Edge(v, d, t));
            graph.get(v).add(new Edge(u, d, t));
        }

        PriorityQueue<Reverser> pq = new PriorityQueue<>();

        for (Edge sEdge : graph.get(n)) {
            pq.offer(new Reverser(sEdge.to, sEdge.breakTime - sEdge.dist));
            bestRecord[sEdge.to] = sEdge.breakTime - sEdge.dist;
        }

        long maxTime = 0;
        boolean canArrived = false;

        while (!pq.isEmpty()) {
            Reverser curr = pq.poll();

            if (curr.pos == 1) {
                canArrived = true;
                maxTime = Math.max(maxTime, curr.time);
            }

            List<Edge> nextEdges = graph.get(curr.pos);

            for (Edge nextEdge : nextEdges) {
                long remainTime = curr.time - nextEdge.dist;
                if (remainTime < 0) continue;

                if (remainTime < bestRecord[nextEdge.to]) continue;

                bestRecord[nextEdge.to] = remainTime;  // 최적 시간 갱신
                pq.offer(new Reverser(nextEdge.to, remainTime));
            }
        }

        System.out.println(canArrived ? maxTime : -1);
    }
}