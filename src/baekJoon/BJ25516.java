package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ25516 {

    static class State {
        int node, depth;

        public State(int node, int depth) {
            this.node = node;
            this.depth = depth;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        boolean[] applePos = new boolean[n];

        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());

        }

        for (int i = 0; i < n - 1; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());

            adjList.get(start).add(end);
            adjList.get(end).add(start);
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        for (int i = 0; i < n ; i++) {
            int apple = Integer.parseInt(stringTokenizer.nextToken());
            if (apple == 1) {
                applePos[i] = true;
            }
        }

        Queue<State> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        int count = 0;

        queue.offer(new State(0,0));
        visited[0] = true;

        while (!queue.isEmpty()) {
            State curr = queue.poll();
            int cNode = curr.node;
            int cDepth = curr.depth;

            if (cDepth == k) {
                System.out.println(count);
                return;
            }

            if (applePos[cNode]) {
                count++;
            }

            List<Integer> ends = adjList.get(cNode);

            for (int end : ends) {
                if (!visited[end]) {
                    queue.offer(new State(end, cDepth+1));
                    visited[end] = true;
                }

            }
        }

        System.out.println(count);

    }
}
