package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ31854 {

    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N * N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N - 1; j++) {
                String sign = stringTokenizer.nextToken();

                if (sign.equals("<")) {
                    graph.get((N * i) + j + 1).add((N * i) + j);
                } else {
                    graph.get((N * i) + j).add((N * i) + j + 1);
                }

            }
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                String sign = stringTokenizer.nextToken();

                if (sign.equals("<")) {
                    graph.get((N * (i + 1)) + j).add((N * i) + j);
                } else {
                    graph.get((N * i) + j).add((N * (i + 1)) + j);
                }
            }
        }

        int[] visited = new int[N * N];
        int seq = 1;

        while (seq <= N * N) {
            int startPoint = getStartPoint(graph, visited);

            visited[startPoint] = seq++;
            processAdjNode(graph, startPoint);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result.append(visited[i * N + j]).append(" ");
            }
            result.append("\n");
        }

        System.out.println(result);
    }

    private static void processAdjNode(List<List<Integer>> graph, Integer startPoint) {

        int left = startPoint - 1;
        int right = startPoint + 1;
        int top = startPoint - N;
        int bottom = startPoint + N;
        int[] adjNodes = {left, right, top, bottom};

        for (Integer adjNode : adjNodes) {
            if (isSafe(adjNode)) {
                graph.get(adjNode).remove(startPoint);
            }
        }
    }

    private static boolean isSafe(int adjNode) {
        return 0 <= adjNode && adjNode < N * N;
    }

    private static int getStartPoint(List<List<Integer>> graph, int[] visited) {
        for (int i = 0; i < graph.size(); i++) {
            List<Integer> adj = graph.get(i);
            if (adj.isEmpty() && visited[i] == 0) {
                return i;
            }
        }
        return 0;
    }
}
