package programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class 가장먼노드 {

    static class Solution {

        static class VertexState {
            int idx, depth;

            public VertexState(int idx, int depth) {
                this.idx = idx;
                this.depth = depth;
            }

        }

        public int solution(int n, int[][] edge) {
            int answer = 0;

            List<List<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                adjList.add(new ArrayList<>());
            }

            for (int[] e : edge) {
                adjList.get(e[0]).add(e[1]);
                adjList.get(e[1]).add(e[0]);
            }

            boolean[] visited = new boolean[n + 1];
            Queue<VertexState> queue = new ArrayDeque<>();
            queue.offer(new VertexState(1, 0));
            visited[1] = true;
            int maxDepth = 0;
            List<Integer> maxVs = new ArrayList<>();

            while (!queue.isEmpty()) {
                VertexState current = queue.poll();
                System.out.println(current.idx);
                System.out.println(current.depth);
                System.out.println();
                List<Integer> adjV = adjList.get(current.idx);

                for (Integer v : adjV) {
                    if (visited[v]) continue;

                    queue.offer(new VertexState(v, current.depth + 1));
                    if (current.depth + 1 > maxDepth) {
                        maxDepth = current.depth;
                        maxVs = new ArrayList<>();
                    }
                    maxVs.add(v);
                    visited[v] = true;
                }
            }

            for (int v : maxVs) {
                System.out.println(v);
            }


            return maxVs.size();
        }
    }
}
