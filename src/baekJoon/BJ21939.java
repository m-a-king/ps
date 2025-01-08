package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class BJ21939 {

    private static class Problem {
        private final int number;
        private final int difficulty;

        public Problem(int number, int difficulty) {
            this.number = number;
            this.difficulty = difficulty;
        }
    }

    private static class Problems {
        private final TreeMap<Integer, TreeSet<Integer>> difficultyTree;
        private final Map<Integer, Integer> numberToDifficulty;

        public Problems() {
            this.difficultyTree = new TreeMap<>();
            this.numberToDifficulty = new HashMap<>();
        }

        public void add(Problem problem) {
            int diff = problem.difficulty;
            int num = problem.number;

            difficultyTree
                    .computeIfAbsent(problem.difficulty, __ -> new TreeSet<>())
                    .add(problem.number);

            numberToDifficulty.put(num, diff);
        }

        public void recommend(int type) {
            final int EASIEST = -1;

            if (type == EASIEST) {
                int easiestNumber = difficultyTree.firstKey();
                int easiestAndSmallestNumber = difficultyTree.get(easiestNumber).first();
                System.out.println(easiestAndSmallestNumber);
                return;
            }

            int hardestNumber = difficultyTree.lastKey();
            int hardestAndBiggestNumber = difficultyTree.get(hardestNumber).last();
            System.out.println(hardestAndBiggestNumber);
        }

        public void solve(Integer number) {
            int difficulty = numberToDifficulty.get(number);

            TreeSet<Integer> numbers = difficultyTree.get(difficulty);
            numbers.remove(number);
            numberToDifficulty.remove(number);

            if (numbers.isEmpty()) {
                difficultyTree.remove(difficulty);
            }
        }
    }

    private static Problems problems;

    public static void main(String[] args) throws IOException {

        problems = new Problems();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int problemCount = Integer.parseInt(bufferedReader.readLine());
        for (int problemIdx = 0; problemIdx < problemCount; problemIdx++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            addProblem(stringTokenizer);
        }

        int commandCount = Integer.parseInt(bufferedReader.readLine());
        for (int commandIdx = 0; commandIdx < commandCount; commandIdx++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            process(stringTokenizer);
        }
    }

    private static void process(StringTokenizer stringTokenizer) {
        String command = stringTokenizer.nextToken();

        if (command.equals("add")) {
            addProblem(stringTokenizer);
            return;
        }
        if (command.equals("recommend")) {
            recommendProblem(stringTokenizer);
            return;
        }
        if (command.equals("solved")) {
            solveProblem(stringTokenizer);
            return;
        }
    }

    private static void addProblem(StringTokenizer stringTokenizer) {
        int problemNumber = Integer.parseInt(stringTokenizer.nextToken());
        int difficulty = Integer.parseInt(stringTokenizer.nextToken());
        problems.add(new Problem(problemNumber, difficulty));
    }

    private static void recommendProblem(StringTokenizer stringTokenizer) {
        int type = Integer.parseInt(stringTokenizer.nextToken());
        problems.recommend(type);
    }

    private static void solveProblem(StringTokenizer stringTokenizer) {
        int problemNumber = Integer.parseInt(stringTokenizer.nextToken());
        problems.solve(problemNumber);
    }
}
