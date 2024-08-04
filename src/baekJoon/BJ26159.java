package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ26159 {

    static int n; // 노드의 수
    static List<List<Integer>> adjList; // 인접 리스트로 그래프를 표현
    static long[] weights; // 간선의 가중치를 저장하는 배열
    static long[] subSize; // 각 노드의 서브트리 크기를 저장하는 배열
    static final long MOD = 1_000_000_007; // 모듈러 값

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine()); // 노드의 수 입력

        adjList = new ArrayList<>(); // 인접 리스트 초기화
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>()); // 각 노드에 대한 리스트를 추가
        }

        StringTokenizer stringTokenizer;
        for (int i = 0; i < n - 1; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            adjList.get(from).add(to); // 단방향 간선 추가
            adjList.get(to).add(from); // 반대 방향으로도 추가하여 양방향 구성
//            System.out.println(adjList);
        }

        weights = new long[n - 1]; // 가중치를 저장하는 배열 초기화
        stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        for (int i = 0; i < n - 1; i++) {
            weights[i] = Long.parseLong(stringTokenizer.nextToken()); // 가중치 입력
        }
        Arrays.sort(weights); // 가중치를 오름차순으로 정렬

        subSize = new long[n + 1]; // 각 노드의 서브트리 크기를 저장하는 배열 초기화

        // 각 노드의 서브트리 크기를 계산
        dfs(1, -1);

        // 결과 계산
        long result = 0;
        List<Long> counts = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            long count = subSize[i] * (n - subSize[i]);
            counts.add(count);
        }

        counts.sort(Collections.reverseOrder()); // 가중치를 내림차순으로 정렬

        for (int i = 0; i < counts.size(); i++) {
            result += (counts.get(i) * weights[i]);
            result %= MOD;
        }
        System.out.print(result); // 결과 출력
    }

    private static long dfs(int node, int parent) {
        subSize[node] = 1; // 나(현재 노드)를 포함한 서브트리 크기 초기화
        for (int edge : adjList.get(node)) {
            if (edge != parent) {
                subSize[node] += dfs(edge, node);
            }
        }
        return subSize[node];
    }
}