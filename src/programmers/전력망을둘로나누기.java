package programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class 전력망을둘로나누기 {

    static class Solution {

        public static void main(String[] args) {
            int solution = solution(9, new int[][]{{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}});
            System.out.println("res = " + solution);
        }

        public static int solution(int n, int[][] wires) {
            int answer = Integer.MAX_VALUE;

            List<List<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i <= n; i++) { // 0 을 빈 리스트로 채움
                adjList.add(new ArrayList<>());
            }

            for (int[] wire : wires) {
                adjList.get(wire[0]).add(wire[1]);
                adjList.get(wire[1]).add(wire[0]);
            }

            int startPoint = 1;
            for (int[] ignoreWire : wires) {
                int ignoreWS = ignoreWire[0];
                int ignoreWE = ignoreWire[1];

                while (startPoint == ignoreWS) {
                    startPoint++;
                }

                adjList.get(ignoreWS).remove(Integer.valueOf(ignoreWE));
                adjList.get(ignoreWE).remove(Integer.valueOf(ignoreWS));

                // bfs
                Queue<Integer> queue = new ArrayDeque<>();
                boolean[] visited = new boolean[n + 1];
                queue.offer(startPoint);

                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    List<Integer> adj = adjList.get(curr);

                    for (int v : adj) {
                        if (!visited[v]) {
                            visited[v] = true;
                            queue.offer(v);
                        }
                    }
                }

                int count = 0;
                for (boolean visit : visited) {
                    if (visit) {
                        count++;
                    }
                }
                answer = Math.min(answer, Math.abs(count * 2 - n));

                adjList.get(ignoreWS).add(ignoreWE);
                adjList.get(ignoreWE).add(ignoreWS);

            }

            return answer;
        }
    }

}
