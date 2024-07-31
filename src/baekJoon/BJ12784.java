package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ12784 {
    private static class Bridge {
        int target, needDynamite;

        public Bridge(int target, int needDynamite) {
            this.target = target;
            this.needDynamite = needDynamite;
        }
    }

    static List<List<Bridge>> adjBridges;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        for (int t = 0; t < tc; t++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());

            if (n == 1) {
                stringBuilder.append(0).append("\n");
                continue;
            }

            adjBridges = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                adjBridges.add(new ArrayList<>());
            }
            visited = new boolean[n + 1];

            for (int i = 1; i <= m; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                int start = Integer.parseInt(stringTokenizer.nextToken());
                int end = Integer.parseInt(stringTokenizer.nextToken());
                int needDynamite = Integer.parseInt(stringTokenizer.nextToken());

                adjBridges.get(start).add(new Bridge(end, needDynamite));
                adjBridges.get(end).add(new Bridge(start, needDynamite));
            }

            stringBuilder.append(dfs(1)).append("\n");

        }

        System.out.println(stringBuilder);

    }

    private static int dfs(int curr) {
        visited[curr] = true;
        int res = 0;
        boolean isLeaf = true;

        for (Bridge bridge : adjBridges.get(curr)) {
            if(visited[bridge.target]) continue;
            isLeaf = false;
            res += Math.min(bridge.needDynamite, dfs(bridge.target));
        }
        int t =3;
        while (t-- > 0) {

        }

        if(isLeaf) return Integer.MAX_VALUE;
        return res;
    }

}

