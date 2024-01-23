package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BJ15654 {

    static StringBuilder stringBuilder = new StringBuilder();
    static int n, m;
    static int[] numbers;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");
        n = parseInt(nm[0]);
        m = parseInt(nm[1]);

        numbers = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        visited = new boolean[n];
        dfs(0);

    }

    private static void dfs(int depth) {

        if (depth == m) {
            System.out.println(stringBuilder.toString().trim());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                stringBuilder.append(numbers[i]).append(" ");
                dfs(depth + 1);
                visited[i] = false;
                stringBuilder.delete(stringBuilder.lastIndexOf(String.valueOf(numbers[i])), stringBuilder.length());

            }
        }
    }
}
