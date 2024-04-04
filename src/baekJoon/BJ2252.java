package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2252 {
    static List<List<Integer>> adjList;
    static int n;
    static int[] inDegree;
    static Queue<Integer> queue;
    static StringBuilder stringBuilder = new StringBuilder();


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        adjList = new ArrayList<>();
        inDegree = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {

            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int front = Integer.parseInt(stringTokenizer.nextToken());
            int back = Integer.parseInt(stringTokenizer.nextToken());

            adjList.get(front).add(back);
            inDegree[back]++;
        }

        queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        bfs();

        System.out.println(stringBuilder);
    }

    private static void bfs() {

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            stringBuilder.append(current).append(" ");

            List<Integer> nexts = adjList.get(current);

            for (int next : nexts) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
    }
}