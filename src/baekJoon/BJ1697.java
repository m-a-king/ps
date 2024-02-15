package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BJ1697 {

    private static class Pos {
        int pos, time;

        public Pos(int pos, int time) {

            this.pos = pos;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = bufferedReader.readLine().split(" ");

        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        Queue<Pos> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[100001];
        visited[n] = true;

        queue.offer(new Pos(n, 0));

        while (!queue.isEmpty()) {
            Pos current = queue.poll();

            if (current.pos == k) {
                System.out.println(current.time);

                return;
            }
            int[] nexts = new int[3];

            nexts[0] = current.pos - 1;
            nexts[1] = current.pos + 1;
            nexts[2] = current.pos * 2;

            for (int next : nexts) {
                if (0 <= next && next < 100001 && !visited[next]) {
                    visited[next] = true;
                    queue.offer(new Pos(next, current.time + 1));
                }
            }
        }
    }
}
