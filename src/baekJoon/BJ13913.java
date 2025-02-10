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
        }

        if (N > K) {
            System.out.println(N - K);
        }

        Queue<State> queue = new ArrayDeque<>();
        int[] visited = new int[K * 2];

        queue.offer(new State(N, 0));

        while (!queue.isEmpty()) {
            State curr = queue.poll();
            int nextTime = curr.time + 1;
            int[] nextPos = {curr.pos - 1, curr.pos + 1, curr.pos * 2};

            for (int next : nextPos) {
                System.out.println("next = " + next);

                if (0 <= next && next < visited.length && visited[next] == 0) {
                    visited[next] = curr.pos;

                    if (next == K) {
                        System.out.println(curr.time + 1);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(K).append(" ");
                        int temp = K;

                        while (true) {
                            System.out.println(Arrays.toString(visited));
                            int before = visited[temp];
                            stringBuilder.append(before).append(" ");
                            temp = before;

                            if (before == N) {
                                System.out.println(stringBuilder);
                                return;
                            }
                        }
                    }

                    queue.offer(new State(next, nextTime));
                }
            }
        }

        System.out.println();


    }
}
