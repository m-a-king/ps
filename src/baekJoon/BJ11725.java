package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static java.lang.Integer.parseInt;


public class BJ11725 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n-1; i++) {
            String[] input = bufferedReader.readLine().split(" ");
            int a = parseInt(input[0]);
            int b = parseInt(input[1]);

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        queue.offer(1);
        visited[1] = true;
        int parent;
        int[] result = new int[n + 1];

        while (!queue.isEmpty()) {
            int current = queue.poll();
            parent = current;

            List<Integer> nextNodes = graph.get(current);

            for (int node : nextNodes) {
                if (!visited[node]) {
                    visited[node] = true;
                    queue.offer(node);
                    result[node] = parent;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            System.out.println(result[i]);

        }
    }
}
