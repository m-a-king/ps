package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ9466_2 {

    static int[] selects;
    static int[] visited;
    static boolean[] completed;
    static int n;
    static int count;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        while (t-- > 0) {
            n = Integer.parseInt(bufferedReader.readLine());
            selects = new int[n + 1];
            visited = new int[n + 1];
            completed = new boolean[n + 1];
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            for (int i = 1; i <= n; i++) {
                selects[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
            count = 0;

            for (int i = 1; i <= n; i++) {
                if (visited[i] > 0) continue;
                dfs(i);
            }

            stringBuilder.append(n - count).append("\n");
        }
        System.out.print(stringBuilder);
    }

    private static void dfs(int curr) {
        int start = curr;

        while (true) {
            visited[curr] = start;
            int next = selects[curr];

            // 다음 노드 방문 안했다면?
            if (visited[next] == 0) {
                curr = next;
                continue;
            }

            // 다음 노드 방문 했다면?
            // next 부터 curr 까지 완료체크
            while (visited[next] == start && !completed[next]) {
                count++;
                completed[next] = true;
                next = selects[next];
            }
            return;






        }


    }
}


