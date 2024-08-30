package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ9466 {

    static int[] selects;
    static boolean[] visited, completed;
    static int n;
    static int count;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        while (t-- > 0) {
            n = Integer.parseInt(bufferedReader.readLine());
            selects = new int[n + 1];
            visited = new boolean[n + 1];
            completed = new boolean[n + 1];
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            for (int i = 1; i <= n; i++) {
                selects[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
            count = 0;

            for (int i = 1; i <= n; i++) {
                dfs(i);
            }


            stringBuilder.append(n-count).append("\n");
        }
        System.out.print(stringBuilder);
    }

    private static void dfs(int curr) {
        if(visited[curr]) return;
        visited[curr] = true;
        int next = selects[curr];

        if (!visited[next]) {
            dfs(next);
        } else {
            if (!completed[next]) {
                while (next != curr) {
                    count++;
                    next = selects[next];
                }
                count++;
            }
        }

        completed[curr] = true;
    }
}