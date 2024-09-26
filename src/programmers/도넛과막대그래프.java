package programmers;

import java.util.*;

public class 도넛과막대그래프 {

    public static void main(String[] args) {

        int[][] edge1 = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};
        int[][] edge2 = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};

        solution(edge2);

    }

    static int[][] edges;
    static Map<Integer, List<Integer>> graph = new HashMap<>();
    static int startNode = -1;
    static int[] answer;
    static Set<Integer> visited = new HashSet<>();

    public static int[] solution(int[][] edge) {
        edges = edge;

        init();

        answer = new int[]{startNode, 0, 0, 0};

        search();

        answer[1] = graph.get(startNode).size() - answer[2] - answer[3];
        System.out.println(Arrays.toString(answer));

        return answer;
    }

    private static void search() {

        for (int adjStart : graph.get(startNode)) {
            System.out.println(adjStart);
            dfs(adjStart);
        }
    }

    private static void dfs(int curr) {

        if (visited.contains(curr)) {
            answer[1]++;
            return;
        }

        visited.add(curr);
        List<Integer> next = graph.getOrDefault(curr, new ArrayList<>());

        if (next.isEmpty()) {
            System.out.println(next.toString());
            answer[2]++;
        }

        if (next.size() >= 2) {
            System.out.println(next.toString());
            answer[3]++;
        }

        for (int n : next) {
            System.out.println(visited.toString());
            System.out.println("n = " + n);

            dfs(n);

        }

    }

    private static void init() {
        Set<Integer> toSet = new HashSet<>();  // to로 등장한 노드를 기록

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            graph.computeIfAbsent(from, key -> new ArrayList<>()).add(to);
            toSet.add(to); // 간선의 도착점 추가

//            List<Integer> list = graph.getOrDefault(from, new ArrayList<>());
//            list.add(to);
//            graph.put(from, list);
        }

//        // 그래프 출력 부분 추가
//        System.out.println("그래프 구조:");
//        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
//            int node = entry.getKey();
//            List<Integer> neighbors = entry.getValue();
//            System.out.println("노드 " + node + " -> 인접 노드들: " + neighbors);
//        }
//
//        System.out.println("toSet = " + toSet);


        for (int from : graph.keySet()) {
            if (toSet.contains(from)) continue;
            if(graph.get(from).size() < 2) continue;


            startNode = from;
        }


    }
}
