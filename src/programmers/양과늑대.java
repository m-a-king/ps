package programmers;

import java.util.*;

public class 양과늑대 {

    public static void main(String[] args) {

//        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
//        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
//
//        System.out.println(solution(info, edges));

        int[] info2 = {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0};
        int[][] edges2 = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}};

        System.out.println(solution(info2, edges2));

    }

    private static class Sheep {
        int idx;
        List<Integer> passWolfs;
        boolean visited = false;

        public Sheep(int idx, List<Integer> passWolfs) {
            this.idx = idx;
            this.passWolfs = passWolfs;
        }
    }

    static List<List<Integer>> adjList = new ArrayList<>();
    static int[] infos;
    static int maxSheep = 0;
    static List<Sheep> sheep = new ArrayList<>();

    public static int solution(int[] info, int[][] edges) {
        infos = info;

        for (int i = 0; i < info.length; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
        }

        checkPassWolf(0, new ArrayDeque<>());

        return countMaxSheep();

    }

    private static int countMaxSheep() {
        Set<Integer> accumulatedWolf = new HashSet<>();
        int sheepCount = 0;

        while (true) {
            int currSheepCount = sheepCount;

            for (Sheep s : sheep) {

                if(s.visited) continue;

                Set<Integer> temp = new HashSet<>(accumulatedWolf);
                temp.addAll(s.passWolfs);

                if (s.passWolfs.isEmpty() || temp.size() < sheepCount) {
                    sheepCount++;
                    s.visited = true;
                    System.out.println(s.idx);
                    accumulatedWolf = temp;
                }
            }

            if (currSheepCount == sheepCount) {
                return sheepCount;
            }
        }
    }

    private static void checkPassWolf(int curr, Deque<Integer> passWolfs) {
        if (infos[curr] == 0) {
            sheep.add(new Sheep(curr, new ArrayList<>(passWolfs)));
            System.out.println("curr = " + curr);
            System.out.println(passWolfs.toString());
            System.out.println();
        }

        for (Integer next : adjList.get(curr)) {
            boolean flag = infos[next] == 1;
            if (flag) passWolfs.push(next);
            checkPassWolf(next, passWolfs);
            if (flag) passWolfs.pop();
        }
    }

}

//    private static void dfs(int curr) {
//        sheepWolf[infos[curr]]++;
//
//        if (sheepWolf[0] <= sheepWolf[1]) {
//            sheepWolf[infos[curr]]--;
//            return;
//        } else {
//            maxSheep = Math.max(maxSheep, sheepWolf[0]);
//        }
//
//
//        for (int next : adjList.get(curr)) {
//            System.out.println("curr = " + curr);
//            System.out.println("next = " + next);
//            System.out.println("sheep = " + sheepWolf[0]);
//            System.out.println("wolf = " + sheepWolf[1]);
//            System.out.println();
//            dfs(next);
//        }
//
//        if(infos[curr] == 1) sheepWolf[infos[curr]]--;
//
//    }
//}
