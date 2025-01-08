package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ21939 {

    private static class Problems {
        private final PriorityQueue<Integer> ascProblemDifficulties;
        private final PriorityQueue<Integer> desProblemDifficulties;
        private final Map<Integer, List<Integer>> difficultyToProblemNumbers;
        private final Map<Integer, Integer> problemNumberToDifficulty;

        private Problems() {
            this.ascProblemDifficulties = new PriorityQueue<>();
            this.desProblemDifficulties = new PriorityQueue<>(Comparator.reverseOrder());
            this.difficultyToProblemNumbers = new HashMap<>();
            this.problemNumberToDifficulty = new HashMap<>();
        }

        public void add(Problem problem) {
            ascProblemDifficulties.offer(problem.difficulty);
            desProblemDifficulties.offer(problem.difficulty);
            difficultyToProblemNumbers
                    .computeIfAbsent(problem.difficulty, __ -> new ArrayList<>())
                    .add(problem.number);
            problemNumberToDifficulty.put(problem.number, problem.difficulty);
        }

        public void recommend(int type) {
            if (type == -1) {
                assert ascProblemDifficulties.peek() != null;
                int difficulty = ascProblemDifficulties.peek();

                List<Integer> problemNumbers = difficultyToProblemNumbers.get(difficulty);
                problemNumbers.sort(Comparator.naturalOrder());
                Integer easiestAndSmallest = problemNumbers.stream().findFirst().orElseThrow();

                System.out.println(easiestAndSmallest);
                return;
            }

            if (type == 1) {
                assert desProblemDifficulties.peek() != null;
                int difficulty = desProblemDifficulties.peek();

                List<Integer> problemNumbers = difficultyToProblemNumbers.get(difficulty);
                problemNumbers.sort(Comparator.reverseOrder());
                Integer hardestAndBiggest = problemNumbers.stream().findFirst().orElseThrow();

                System.out.println(hardestAndBiggest);
                return;
            }
        }

        public void solve(Integer number) {
            int difficulty = problemNumberToDifficulty.get(number);
            ascProblemDifficulties.remove(difficulty);
            desProblemDifficulties.remove(difficulty);
            List<Integer> problemNumbers = difficultyToProblemNumbers.get(difficulty);
            problemNumbers.remove(number);
        }
    }

    private static class Problem {
        private final int number;
        private final int difficulty;

        public Problem(int number, int difficulty) {
            this.number = number;
            this.difficulty = difficulty;
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
