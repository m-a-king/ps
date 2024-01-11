package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BJ9019 {

    static int start;
    static int target;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tc = parseInt(bufferedReader.readLine());

        for (int t = 0; t < tc; t++) {
            int[] input2 = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            start = input2[0];
            target = input2[1];

            bfs();

        }
    }

    private static void bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[10000];
        queue.offer(new Node(start, ""));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.number == target) {
                System.out.println(current.path);
                return;
            }

            int D = (2 * current.number) % 10000;
            int S = (current.number == 0) ? 9999 : current.number - 1;
            int L = (current.number % 1000) * 10 + current.number / 1000;
            int R = (current.number / 10) + ((current.number % 10) * 1000);

            if (!visited[D]) {
                queue.offer(new Node(D, current.path + "D"));
                visited[D] = true;
            }
            if (!visited[S]) {
                queue.offer(new Node(S, current.path + "S"));
                visited[S] = true;
            }
            if (!visited[L]) {
                queue.offer(new Node(L, current.path + "L"));
                visited[L] = true;
            }
            if (!visited[R]) {
                queue.offer(new Node(R, current.path + "R"));
                visited[R] = true;
            }
        }
    }

    static class Node {
        int number;
        String path;

        Node(int number, String path) {
            this.number = number;
            this.path = path;
        }
    }

}
