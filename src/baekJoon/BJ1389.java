package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BJ1389 {

    static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = bufferedReader.readLine().split(" ");

        int peopleCount = parseInt(input1[0]);
        int relation = parseInt(input1[1]);


        for (int i = 0; i < relation; i++) {
            int[] input2 = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            List<Integer> list = graph.getOrDefault(input2[0], new ArrayList<>());
            list.add(input2[1]);
            graph.put(input2[0], list);

            list = graph.getOrDefault(input2[1], new ArrayList<>());
            list.add(input2[0]);
            graph.put(input2[1], list);
        }

        int res = Integer.MAX_VALUE;
        int solution = -1;
        for (int i = 1; i <= peopleCount; i++) {
            if (res > calcKB(i, peopleCount)) {
                res = calcKB(i, peopleCount);
                solution = i;
            }
        }

        System.out.println(solution);
    }

    private static int calcKB(int target, int peopleCount) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[peopleCount + 1];
        int[] distance = new int[peopleCount + 1];

        queue.add(target);
        visited[target] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int neighbor : graph.getOrDefault(now, new ArrayList<>())) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    distance[neighbor] = distance[now] + 1;
                }
            }
        }

        int sum = 0;
        for (int d : distance) {
            sum += d;
        }

        return sum;
    }
}
