package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ11403 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 0; j < n; j++) {
                adjList.get(i).add(Integer.valueOf(stringTokenizer.nextToken()));
            }
        }

        int[][] res = new int[n][n];
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < n; i++) {

            boolean[] visited = new boolean[n];
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(i);

            while (!queue.isEmpty()) {
                int current = queue.poll();

                List<Integer> list = adjList.get(current);

                for (int x = 0; x < n; x++) {
                    if (list.get(x) == 1 && !visited[x]) {
                        visited[x] = true;
                        queue.offer(x);
                    }
                }
            }

            for (int j = 0; j < n; j++) {
                int s = visited[j] ? 1 : 0;
                stringBuilder.append(s).append(" ");
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder);


    }
}
