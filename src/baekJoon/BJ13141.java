package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ13141 {

    private static class Edge implements Comparable<Edge> {
        int to;
        double length;
        double speed;  // 속도 필드 추가

        public Edge(int to, double length, double speed) {
            this.to = to;
            this.length = length;
            this.speed = speed;
        }

        // 두 번째 생성자: 기본적으로 속도 1로 초기화
        public Edge(int to, double length) {
            this(to, length, 1.0);
        }

        @Override
        public int compareTo(Edge o) {
            int lengthComparison = Double.compare(this.length / this.speed, o.length / o.speed);

            if (lengthComparison == 0) {
                return Double.compare(this.speed, o.speed);
            }

            return lengthComparison;
        }
    }

    static List<List<Edge>> graph;
    static boolean[] visited;

    static double minTime = Double.MAX_VALUE;
    static int n;

    static int[][] minDist;
    static int[][] maxDist;


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        minDist = new int[n + 1][n + 1];
        maxDist = new int[n + 1][n + 1];

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 최소 최대 초기화
        for (int i = 0; i <= n; i++) {
            Arrays.fill(minDist[i], Integer.MAX_VALUE);
            Arrays.fill(maxDist[i], 0);
        }

        // 입력 처리
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            int length = Integer.parseInt(stringTokenizer.nextToken());
            // 최소 거리 업데이트
            minDist[from][to] = Math.min(minDist[from][to], length);
            minDist[to][from] = Math.min(minDist[to][from], length);

            // 최대 거리 업데이트
            maxDist[from][to] = Math.max(maxDist[from][to], length);
            maxDist[to][from] = Math.max(maxDist[to][from], length);
        }

        // 간선 추가 (최소 거리와 최대 거리만 반영)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (minDist[i][j] < Integer.MAX_VALUE) { // 유효한 간선이 있는 경우
                    if (minDist[i][j] == maxDist[i][j]) {
                        graph.get(i).add(new Edge(j, minDist[i][j])); // 최소 거리 간선 추가
                        continue;
                    }
                    graph.get(i).add(new Edge(j, minDist[i][j])); // 최소 거리 간선 추가
                    graph.get(i).add(new Edge(j, maxDist[i][j])); // 최대 거리 간선 추가
                }
            }
        }

        for (int start = 1; start <= n; start++) {
            visited = new boolean[n + 1];
            visited[start] = true;
            bfs(start);
        }

        System.out.println(minTime);
    }

    private static void bfs(int start) {
        double time = 0;

        PriorityQueue<Edge> currPQ = new PriorityQueue<>();
        List<Edge> temp = new ArrayList<>();

        for (Edge edge : graph.get(start)) {
            currPQ.offer(edge);
        }

        while (!currPQ.isEmpty()) {
            double optimizedTime = calcTime(currPQ.peek());
            time += optimizedTime;
            if (time > minTime) {
                return;
            }

            int currentSize = currPQ.size();  // 큐의 현재 크기 저장
            System.out.println(currentSize);

            for (int i = 0; i < currentSize; i++) {
                Edge curr = currPQ.poll();

                double leftLength = curr.length - (optimizedTime * curr.speed);

                if (leftLength == 0) {
                    if (visited[curr.to]) continue;
                    visited[curr.to] = true;

                    for (Edge nEdge : graph.get(curr.to)) {
                        if (!visited[nEdge.to]) {
                            temp.add(nEdge);
                        }
                    }
                } else {
                    temp.add(new Edge(curr.to, leftLength, curr.speed));  // 새로운 객체로 삽입
                }
            }

            for (Edge t : temp) {
                if (visited[t.to]) {
                    t.speed = 2;  // 속도를 2로 설정
                }
                currPQ.offer(t);  // 변경된 요소를 임시 큐에 삽입
            }

            temp = new ArrayList<>();
        }

        minTime = Math.min(minTime, time);
    }

    private static double calcTime(Edge edge) {
        return edge.length / edge.speed;
    }
}