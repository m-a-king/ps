package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ31854 {

    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        int totalNodes = N * N;

        int[] inDegree = new int[totalNodes];

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < totalNodes; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N - 1; j++) {
                String sign = stringTokenizer.nextToken();

                int left = (N * i) + j;
                int right = left + 1;

                if (sign.equals("<")) {
                    graph.get(left).add(right);
                    inDegree[right]++;
                } else {
                    graph.get(right).add(left);
                    inDegree[left]++;
                }
            }
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                String sign = stringTokenizer.nextToken();

                int top = (N * i) + j;
                int bottom = top + N;

                if (sign.equals("<")) {
                    graph.get(top).add(bottom);
                    inDegree[bottom]++;
                } else {
                    graph.get(bottom).add(top);
                    inDegree[top]++;
                }
            }
        }

        int[] visited = new int[totalNodes];
        int seq = 1;

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < totalNodes; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            visited[u] = seq++;
            for (int v : graph.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
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
}
