package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ13913 {

    private static class State {
        int pos;
        int time;

        public State(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        if (N == K) {
            System.out.println(0);
            System.out.println(N);
            return;
        }

        if (N > K) {
            System.out.println(N - K);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = N; i >= K; i--) {
                stringBuilder.append(i).append(" ");
            }
            System.out.println(stringBuilder);
            return;
        }

        List<Integer> result = new ArrayList<>();

        Queue<State> queue = new ArrayDeque<>();
        int[] visited = new int[K * 2];
        Arrays.fill(visited, -1);

        queue.offer(new State(N, 0));

        while (!queue.isEmpty()) {
            State curr = queue.poll();
            int nextTime = curr.time + 1;
            int[] nextPos = {curr.pos - 1, curr.pos + 1, curr.pos * 2};

            for (int next : nextPos) {

                if (0 <= next && next < visited.length && visited[next] == -1) {
                    visited[next] = curr.pos;

                    if (next == K) {
                        result.add(K);
                        int temp = K;

                        while (true) {
                            int before = visited[temp];
                            result.add(before);
                            temp = before;

                            if (before == N) {
                                printResult(result);
                                return;
                            }
                        }
                    }
                    queue.offer(new State(next, nextTime));
                }
            }
        }
    }

    private static void printResult(List<Integer> result) {
        System.out.println(result.size() - 1);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = result.size() - 1; i >= 0; i--) {
            int path = result.get(i);
            stringBuilder.append(path).append(" ");
        }
        System.out.println(stringBuilder);
    }
}
