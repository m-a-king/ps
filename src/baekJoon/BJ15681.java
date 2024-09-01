package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ15681 {

    static List<List<Integer>> tree;
    static boolean[] visited;
    static int[] subCnt;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int r = Integer.parseInt(stringTokenizer.nextToken());
        int q = Integer.parseInt(stringTokenizer.nextToken());

        tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        visited = new boolean[n + 1];
        subCnt = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());

            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        dfs(r);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < q; i++) {
            stringBuilder.append(subCnt[Integer.parseInt(bufferedReader.readLine())]).append("\n");

        }

        System.out.print(stringBuilder);
    }

    private static int dfs(int curr) {
        visited[curr] = true;
        int res = 0;

        for (int next : tree.get(curr)) {
            if(visited[next]) continue;

            res += dfs(next);
        }

        subCnt[curr] = res + 1;
        return res + 1;
    }

}
