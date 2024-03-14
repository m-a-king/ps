package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ13549 {

    private static class Subin {
        int pos, time;

        public Subin(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }

    }

    static int s;
    static int e;
    static boolean[] visited;
    static int[] nextPos = new int[3];


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        s = Integer.parseInt(stringTokenizer.nextToken());
        e = Integer.parseInt(stringTokenizer.nextToken());

        if (s > e) {
            System.out.println(s - e);
            return;
        } else if (s == e) {
            System.out.println(0);
            return;
        }

        Deque<Subin> deque = new ArrayDeque<>();
        visited = new boolean[100001];
        deque.offer(new Subin(s, 0));
        visited[s] = true;

        while (!deque.isEmpty()) {
            Subin current = deque.poll();

            if (current.pos == e) {
                System.out.println(current.time);
                return;
            }

            nextPos[0] = current.pos * 2;
            nextPos[1] = current.pos - 1;
            nextPos[2] = current.pos + 1;

            for (int i = 0; i < 3; i++) {
                if (canMove(nextPos[i])) {
                    visited[nextPos[i]] = true;
                    if (i == 0) {
                        deque.offerFirst(new Subin(nextPos[i], current.time));
                    } else {
                        deque.offerLast(new Subin(nextPos[i], current.time + 1));
                    }
                }
            }

        }
    }

    private static boolean canMove(int pos) {
        return 0 <= pos && pos < 100001 && !visited[pos];
    }
}
