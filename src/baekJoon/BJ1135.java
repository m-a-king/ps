package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1135 {
    static List<List<Integer>> adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        for (int i = 0; i < n; i++) {
            int myParent = Integer.parseInt(stringTokenizer.nextToken());
            if (myParent == -1) continue;
            adjList.get(myParent).add(i);
        }

        // 가장 긴 시간을 소모하는 부하의 시간을 반환
        System.out.println(dfs(0, 0));
    }

    private static int dfs(int parent, int level) {
        printIndented("노드 " + parent + "에 대한 DFS 시작", level);
        List<Integer> children = adjList.get(parent);
        List<Integer> times = new ArrayList<>();

        // 리프 노드인 경우 0을 반환
        if (children.isEmpty()) {
            printIndented("노드 " + parent + "는 리프 노드입니다. 0을 반환합니다.", level);
            return 0;
        }

        // 모든 자식 노드에 대해 DFS를 수행하여 시간을 계산
        for (int child : children) {
            int time = dfs(child, level + 1);
            times.add(time);
            printIndented("자식 노드 " + child + "에 대한 DFS 호출 결과: " + time, level + 1);
        }

        // 내림차순으로 정렬하여 가장 오래 걸리는 순서대로 처리
        times.sort(Collections.reverseOrder());

        int maxTime = 0;
        // 각 자식 노드를 순서대로 처리하여 시간을 계산
        for (int i = 0; i < times.size(); i++) {
            maxTime = Math.max(maxTime, times.get(i) + i + 1);
            printIndented("자식 " + children.get(i) + " 처리 중: time = " + times.get(i) + ", i = " + i + ", maxTime = " + maxTime, level + 1);
        }

        printIndented("노드 " + parent + "에서 반환: maxTime = " + maxTime, level);
        return maxTime;
    }

    private static void printIndented(String message, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("    "); // 네 칸 공백을 사용하여 인덴팅
        }
        System.out.println(message);
    }
}