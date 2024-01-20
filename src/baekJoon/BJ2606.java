package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BJ2606 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int computerCount = parseInt(bufferedReader.readLine());
        int lineCount = parseInt(bufferedReader.readLine());

        List<List<Integer>> network = new ArrayList<>();

        for (int i = 0; i <= computerCount; i++) {
            network.add(new ArrayList<>());
        }

        for (int i = 0; i < lineCount; i++) {
            String[] line = bufferedReader.readLine().split(" ");
            int start = parseInt(line[0]);
            int end = parseInt(line[1]);

            List<Integer> list = network.get(start);
            list.add(end);
            list = network.get(end);
            list.add(start);

        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);

        boolean[] visited = new boolean[computerCount + 1];
        visited[1] = true;
        int count = 0;


        while (!queue.isEmpty()) {
            int current = queue.poll();

            List<Integer> nextComputers = network.get(current);

            for (Integer next : nextComputers) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                    count++;

                }
            }

        }

        System.out.println(count);

    }
}
