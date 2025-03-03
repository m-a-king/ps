package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ4196 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(bufferedReader.readLine());

        while (tc-- > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int N = Integer.parseInt(stringTokenizer.nextToken());
            int M = Integer.parseInt(stringTokenizer.nextToken());

            // 도미노의 특성
            List<List<Integer>> outGraph = new ArrayList<>();
            List<List<Integer>> inGraph = new ArrayList<>();

            for (int i = 0; i <= N; i++) {
                outGraph.add(new ArrayList<>());
                inGraph.add(new ArrayList<>());
            }

            while (M-- > 0) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                int y = Integer.parseInt(stringTokenizer.nextToken());

                outGraph.get(x).add(y);
                inGraph.get(y).add(x);
            }

            List<List<Integer>> SCCs = findSCCs(N, outGraph, inGraph);

            // 각 도미노 블록들에게 SCC 번호를 매김
            int idx = 1;
            int[] sccIdx = new int[N + 1];
            for (List<Integer> scc : SCCs) {
                for (int v : scc) {
                    sccIdx[v] = idx;
                }
                idx++;
            }

            // 각 SCC들에게 진입 간선이 있는지 확인
            boolean[] hasIndegree = new boolean[idx];
            for (int i = 1; i <= N; i++) {
                for (int v : outGraph.get(i)) {
                    if (sccIdx[i] != sccIdx[v]) {
                        hasIndegree[sccIdx[v]] = true;
                    }
                }
            }

            // 진입 간선이 없다면 수동으로 넘어뜨려야 함
            int result = 0;
            for (int i = 1; i < idx; i++) {
                if (!hasIndegree[i]) {
                    result++;
                }
            }

            System.out.println(result);
        }
    }

    // DFS 2번으로 SCC 탐색 (코사라주 알고리즘)
    private static List<List<Integer>> findSCCs(int N, List<List<Integer>> outGraph, List<List<Integer>> inGraph) {
        Stack<Integer> reversePostorder = new Stack<>();
        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            dfs(outGraph, i, visited, reversePostorder);
        }

        visited = new boolean[N + 1];
        List<List<Integer>> SCCs = new ArrayList<>();

        while (!reversePostorder.isEmpty()) {
            List<Integer> SCC = new ArrayList<>();
            dfs(inGraph, reversePostorder.pop(), visited, SCC);
            if (SCC.isEmpty()) {
                continue;
            }
            SCCs.add(SCC);
        }

        return SCCs;
    }

    // 후위 순회의 결과의 역순을 저장
    private static void dfs(List<List<Integer>> outGraph, int curr, boolean[] visited, Stack<Integer> reversePostorder) {
        if (visited[curr]) {
            return;
        }
        visited[curr] = true;
        for (int next : outGraph.get(curr)) {
            dfs(outGraph, next, visited, reversePostorder);
        }
        reversePostorder.push(curr);
    }

    // SCC를 저장
    private static void dfs(List<List<Integer>> inGraph, int curr, boolean[] visited, List<Integer> SCC) {
        if (visited[curr]) {
            return;
        }
        visited[curr] = true;
        SCC.add(curr);

        for (int next : inGraph.get(curr)) {
            dfs(inGraph, next, visited, SCC);
        }
    }
}
