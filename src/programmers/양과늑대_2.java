package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 양과늑대_2 {

    public static void main(String[] args) {

        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7},
                {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};

        System.out.println(solution(info, edges));
    }

    static List<Integer>[] adjList;
    static int[] info;
    static int maxSheep;
    static Set<String> visitedStates = new HashSet<>();

    public static int solution(int[] infoInput, int[][] edges) {
        info = infoInput;
        int n = info.length;
        adjList = new List[n];

        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adjList[edge[0]].add(edge[1]);
        }

        List<Integer> possibleNodes = new ArrayList<>();
        possibleNodes.add(0);
        dfs(0, 0, 0, possibleNodes);

        return maxSheep;
    }

    private static void dfs(int curr, int sheepCount, int wolfCount, List<Integer> possibleNodes) {
        System.out.println("Entering dfs: curr=" + curr + ", sheepCount=" + sheepCount + ", wolfCount=" + wolfCount + ", possibleNodes=" + possibleNodes);

        if (info[curr] == 0) {
            sheepCount++;
            maxSheep = Math.max(maxSheep, sheepCount);
            if (maxSheep == info.length) return;
        } else {
            wolfCount++;
        }

        // 상태 키 생성 및 중복 검사
        List<Integer> stateNodes = new ArrayList<>(possibleNodes);
        Collections.sort(stateNodes);
        String stateKey = curr + "," + sheepCount + "," + wolfCount + "," + stateNodes.toString();
        if (visitedStates.contains(stateKey)) {
            System.out.println("Duplicate computation detected at node " + curr + " with state: " + stateKey);
            return;
        }
        visitedStates.add(stateKey);

        if (sheepCount > wolfCount) {
            List<Integer> nextPossibleNodes = new ArrayList<>(possibleNodes);
            nextPossibleNodes.remove((Integer) curr);
            nextPossibleNodes.addAll(adjList[curr]);

            System.out.println("Current node: " + curr + ", Next possible nodes: " + nextPossibleNodes);

            for (Integer next : nextPossibleNodes) {
                System.out.println("At node " + curr + ", exploring next node: " + next);
                dfs(next, sheepCount, wolfCount, nextPossibleNodes);
            }
        }
    }
}