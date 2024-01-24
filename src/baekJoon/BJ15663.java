package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BJ15663 {

    static int n, m;
    static int[] numbers;
    static StringBuilder stringBuilder = new StringBuilder();
    static int[] res;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");

        n = parseInt(nm[0]);
        m = parseInt(nm[1]);

        numbers = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        res = new int[m];
        visited = new boolean[n];
        dfs(0, 0);

        System.out.println(stringBuilder);

    }

    private static void dfs(int current, int depth) {

        if (depth == m) {
            for (int r : res) {
                stringBuilder.append(r).append(" ");
            }
            stringBuilder.append("\n");

            return;
        }

        int pre = -1;
        for (int i = current; i < n; i++) {
            if (!visited[i] && pre != numbers[i]) {
                visited[i] = true;
                pre = numbers[i];
                res[depth] = numbers[i];
                dfs(0, depth + 1);
                visited[i] = false;
            }
        }
    }
}
