package programmers;

import java.util.*;

public class 양과늑대 {

    public static void main(String[] args) {

        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};

        System.out.println(solution(info, edges));

//        int[] info2 = {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0};
//        int[][] edges2 = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}};
//
//        System.out.println(">>> answer: " + solution(info2, edges2));

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

    static int[] seq;
    static int sheepCount;
    static int initSheep = 0;
    static boolean[] visited;

    private static int countMaxSheep() {
        sheepCount = 0;
        List<Sheep> temp = new ArrayList<>();

        for (Sheep s : sheep) {
            if (s.passWolfs.isEmpty()) {
                sheepCount++;
                continue;
            }
            temp.add(s);
        }

        initSheep = sheepCount;
        sheep = temp;
        seq = new int[sheep.size()];
        visited = new boolean[sheep.size()];

        for (int i = 0; i < sheep.size(); i++) {
            if (visited[i]) continue;

            visited[i] = true;
            seq[0] = i;

            permutation(1);

            visited[i] = false;
        }

        return sheepCount;
    }

    static Set<Integer> accumulatedWolf;

    private static void permutation(int depth) {
        if (depth == sheep.size()) {
            int newSheepCount = initSheep;
            accumulatedWolf = new HashSet<>();

            for (int i = 0; i < depth; i++) {
                Sheep curr = sheep.get(seq[i]);

                Set<Integer> temp = new HashSet<>(accumulatedWolf);

                temp.addAll(curr.passWolfs);

                if (temp.size() < newSheepCount) {
                    accumulatedWolf = temp;
                    newSheepCount++;
                }
            }

            sheepCount = Math.max(sheepCount, newSheepCount);
            return;
        }

        for (int i = 0; i < sheep.size(); i++) {
            if (visited[i]) continue;

            visited[i] = true;
            seq[depth] = i;

            permutation(depth + 1);

            visited[i] = false;
        }

    }

    private static void checkPassWolf(int curr, Deque<Integer> passWolfs) {
        if (infos[curr] == 0) {
            sheep.add(new Sheep(curr, new ArrayList<>(passWolfs)));
        }

        for (Integer next : adjList.get(curr)) {
            boolean flag = infos[next] == 1;
            if (flag) passWolfs.push(next);
            checkPassWolf(next, passWolfs);
            if (flag) passWolfs.pop();
        }
    }
}

