package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ15649 {

    static int n, m, count;

    static StringBuilder stringBuilder = new StringBuilder();
    static Stack<Integer> selected = new Stack<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        // 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        visited = new boolean[n + 1];

        selectNum(0);

        System.out.println(stringBuilder);

    }

    private static void selectNum(int depth) {
        if (depth == m) {
            for (int num : selected) {
                stringBuilder.append(num).append(" ");

            }
            stringBuilder.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                selected.push(i);
                visited[i] = true;
                selectNum(depth + 1);
                selected.pop();
                visited[i] = false;
            }
        }
    }
}
