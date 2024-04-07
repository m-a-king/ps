package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1197 {

    private static class Edge implements Comparable<Edge> {
        int start, target, weight;

        public Edge(int start, int target, int weight) {
            this.start = start;
            this.target = target;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight < o.weight ? -1 : 1;
        }
    }

    private static int[] parent; // 노드의 부모를 저장하는 배열

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        int v = Integer.parseInt(stringTokenizer.nextToken());
        int e = Integer.parseInt(stringTokenizer.nextToken());

        parent = new int[v + 1]; // 부모 배열 초기화
        for (int i = 1; i <= v; i++) {
            parent[i] = i; // 초기에는 모든 노드가 자신의 부모입니다.
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>();


        for (int i = 0; i < e; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int target = Integer.parseInt(stringTokenizer.nextToken());
            int weight = Integer.parseInt(stringTokenizer.nextToken());
            queue.offer(new Edge(start, target, weight));
        }

        int res = 0;
        while (!queue.isEmpty()) {
            Edge currentEdge = queue.poll();

            if (findParent(currentEdge.start) == findParent(currentEdge.target)) {
                continue;
            }
            union(currentEdge.start, currentEdge.target);
            res += currentEdge.weight;
        }

        System.out.println(res);
    }

    private static int findParent(int node) {
        // 현재 노드가 부모(최상위 노드)라면 반환
        if (parent[node] == node) {
            return node;
        }
        // 현재 노드가 부모가 아니라면 부모를 찾음
        else {
            parent[node] = findParent(parent[node]);
            return parent[node];
        }
    }

    private static void union(int node1, int node2) {
        int parent1 = findParent(node1);
        int parent2 = findParent(node2);

        if (parent1 > parent2) {
            parent[parent2] = parent1;
        } else {
            parent[parent1] = parent2;
        }
    }
}