package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ31932 {

    private static class Edge {
        int to;
        int dist, breakTime;

        public Edge(int to, int dist, int breakTime) {
            this.to = to;
            this.dist = dist;
            this.breakTime = breakTime;
        }
    }

    private static class Node implements Comparable<Node> {
        int idx;
        int deadLine;

        public Node(int idx, int deadLine) {
            this.idx = idx;
            this.deadLine = deadLine;
        }

        @Override
        public int compareTo(Node other) {
            return other.deadLine - this.deadLine;
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

            if (d > t) continue;

            graph.get(u).add(new Edge(v, d, t));
            graph.get(v).add(new Edge(u, d, t));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] bestDeadLine = new int[n + 1];
        Arrays.fill(bestDeadLine, -1);

        for (Edge edge : graph.get(n)) {
            pq.offer(new Node(edge.to, edge.breakTime - edge.dist));
            bestDeadLine[n] = Math.max(bestDeadLine[n], edge.breakTime);
            bestDeadLine[edge.to] = edge.breakTime - edge.dist;
        }

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if(curr.deadLine < bestDeadLine[curr.idx]) continue;
            bestDeadLine[curr.idx] = curr.deadLine;

            if (curr.idx == 1) {
                System.out.println(curr.deadLine);
                return;
            }

            for (Edge adjEdge : graph.get(curr.idx)) {
                int nextNodeDeadLine = curr.deadLine - adjEdge.dist;
                if (nextNodeDeadLine < 0) continue;

                if (curr.deadLine > adjEdge.breakTime) {
                    nextNodeDeadLine = adjEdge.breakTime- adjEdge.dist;
                }

                if (nextNodeDeadLine <= bestDeadLine[adjEdge.to]) continue; // 있는게 낫긴 한듯 ?
                bestDeadLine[adjEdge.to] = nextNodeDeadLine; // 있는게 낫긴 한듯 ?

                pq.offer(new Node(adjEdge.to, nextNodeDeadLine));
            }
        }

        System.out.println(-1);
    }
}