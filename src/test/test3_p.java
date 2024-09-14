package test;

import java.util.*;

public class test3_p {

    static int length;
    static int[][] diceS;
    static boolean[] visited;
    static int maxWin = 0;
    static int[] answer;

    public static int[] solution(int[][] dice) {
        length = dice.length;
        diceS = dice;
        visited = new boolean[length];
        answer = new int[length / 2];

        selectDice(0, 0);

        return answer;
    }

    private static void selectDice(int start, int depth) {
        if (depth == length / 2) {
            System.out.println("\n[팀 구성 완료]");
            System.out.print("팀 1 주사위 인덱스: ");
            for (int i = 0; i < length; i++) {
                if (visited[i]) {
                    System.out.print((i + 1) + " ");
                }
            }
            System.out.print("\n팀 2 주사위 인덱스: ");
            for (int i = 0; i < length; i++) {
                if (!visited[i]) {
                    System.out.print((i + 1) + " ");
                }
            }
            System.out.println();

            int winCount = calcWin();
            System.out.println("현재 팀 구성의 승리 횟수: " + winCount);

            if (winCount > maxWin) {
                System.out.println("최대 승리 횟수를 갱신했습니다!");
                maxWin = winCount;

                int k = 0;
                for (int j = 0; j < visited.length; j++) {
                    if (visited[j]) {
                        answer[k++] = j + 1;
                    }
                }
            }
            return;
        }

        for (int i = start; i < length; i++) {
            visited[i] = true;
            selectDice(i + 1, depth + 1);
            visited[i] = false;
        }
    }

    private static int calcWin() {
        List<int[]> team1 = new ArrayList<>();
        List<int[]> team2 = new ArrayList<>();

        // 팀 나누기
        for (int i = 0; i < length; i++) {
            if (visited[i]) {
                team1.add(diceS[i]);
            } else {
                team2.add(diceS[i]);
            }
        }

        // 각 팀의 가능한 합 계산
        Map<Integer, Integer> team1SumCounts = generateSumCounts(team1, "팀1");
        Map<Integer, Integer> team2SumCounts = generateSumCounts(team2, "팀2");

        // 합을 정렬된 리스트로 변환
        List<Integer> team1Sums = new ArrayList<>(team1SumCounts.keySet());
        List<Integer> team2Sums = new ArrayList<>(team2SumCounts.keySet());
        Collections.sort(team1Sums);
        Collections.sort(team2Sums);

        // 팀 1의 가능한 합과 빈도 출력
        System.out.println("\n[팀 1의 가능한 합과 빈도]");
        for (int sum : team1Sums) {
            System.out.println("합계: " + sum + ", 빈도: " + team1SumCounts.get(sum));
        }

        // 팀 2의 가능한 합과 빈도 출력
        System.out.println("\n[팀 2의 가능한 합과 빈도]");
        for (int sum : team2Sums) {
            System.out.println("합계: " + sum + ", 빈도: " + team2SumCounts.get(sum));
        }

        int winCount = 0;
        int idxTeam2 = 0;
        int totalTeam2Counts = 0;

        // 승리 횟수 계산
        System.out.println("\n[승리 횟수 계산 시작]");
        for (int sum1 : team1Sums) {
            int count1 = team1SumCounts.get(sum1);
            System.out.println("팀 1 합계: " + sum1 + ", 빈도: " + count1);

            // 팀 2의 합계 중 팀 1의 현재 합계보다 작은 합계의 빈도 누적
            while (idxTeam2 < team2Sums.size() && team2Sums.get(idxTeam2) < sum1) {
                int sum2 = team2Sums.get(idxTeam2);
                int count2 = team2SumCounts.get(sum2);
                totalTeam2Counts += count2;
                System.out.println("  팀 2 합계: " + sum2 + " (빈도: " + count2 + ") 누적된 팀 2 빈도 합계: " + totalTeam2Counts);
                idxTeam2++;
            }

            int winCountForSum1 = count1 * totalTeam2Counts;
            System.out.println("  현재 팀 1 합계로 이길 수 있는 경우의 수: " + winCountForSum1);
            winCount += winCountForSum1;
            System.out.println("  누적 승리 횟수: " + winCount);
        }

        System.out.println("[승리 횟수 계산 종료]\n");

        return winCount;
    }

    // 합의 빈도를 계산하는 메서드 (합계 계산 과정 출력 포함)
    private static Map<Integer, Integer> generateSumCounts(List<int[]> team, String teamName) {
        Map<Integer, Integer> sumCounts = new HashMap<>();
        sumCounts.put(0, 1);

        System.out.println("\n[" + teamName + " 합계 계산 과정]");
        int diceNumber = 1;

        for (int[] dice : team) {
            Map<Integer, Integer> newSumCounts = new HashMap<>();
            System.out.println("주사위 " + diceNumber + "의 면: " + Arrays.toString(dice));
            for (Map.Entry<Integer, Integer> entry : sumCounts.entrySet()) {
                int currentSum = entry.getKey();
                int currentCount = entry.getValue();
                for (int face : dice) {
                    int newSum = currentSum + face;
                    newSumCounts.put(newSum, newSumCounts.getOrDefault(newSum, 0) + currentCount);
                    System.out.println("현재 합계: " + currentSum + " + 면 " + face + " = 새로운 합계: " + newSum + " (빈도: " + newSumCounts.get(newSum) + ")");
                }
            }
            sumCounts = newSumCounts;
            diceNumber++;
            System.out.println(teamName + "의 현재 가능한 합계와 빈도: " + sumCounts);
        }

        return sumCounts;
    }

    public static void main(String[] args) {
        int[][] input = {
                {1, 2, 3, 4, 5, 6},
                {3, 3, 3, 3, 4, 4},
                {1, 3, 3, 4, 4, 4},
                {1, 1, 4, 4, 5, 5}
        };
        int[] answer = solution(input);

        System.out.println("\n최대 승리 횟수를 가진 팀 구성:");
        for (int idx : answer) {
            System.out.println("주사위 인덱스: " + idx);
        }
        System.out.println("최대 승리 횟수: " + maxWin);
    }
}



/*
*
* import java.util.*;

public class Solution {

    static int length;
    static int[][] diceS;
    static boolean[] visited;
    static int maxWin = 0;
    static int[] answer;

    public static int[] solution(int[][] dice) {
        length = dice.length;
        diceS = dice;
        visited = new boolean[length];
        answer = new int[length / 2];

        selectDice(0, 0);

        return answer;
    }

    private static void selectDice(int start, int depth) {
        if (depth == length / 2) {

            int winCount = calcWin();

            if (winCount > maxWin) {
                maxWin = winCount;

                int k = 0;
                for (int j = 0; j < visited.length; j++) {
                    if (visited[j]) {
                        answer[k++] = j + 1;
                    }
                }
            }
            return;
        }

        for (int i = start; i < length; i++) {
            visited[i] = true;
            selectDice(i + 1, depth + 1);
            visited[i] = false;
        }
    }

    private static int calcWin() {
        List<int[]> team1 = new ArrayList<>();
        List<int[]> team2 = new ArrayList<>();

        // 팀 나누기
        for (int i = 0; i < length; i++) {
            if (visited[i]) {
                team1.add(diceS[i]);
            } else {
                team2.add(diceS[i]);
            }
        }

        // 각 팀의 가능한 합 계산
        Map<Integer, Integer> team1SumCounts = generateSumCounts(team1);
        Map<Integer, Integer> team2SumCounts = generateSumCounts(team2);

        // 합을 정렬된 리스트로 변환
        List<Integer> team1Sums = new ArrayList<>(team1SumCounts.keySet());
        List<Integer> team2Sums = new ArrayList<>(team2SumCounts.keySet());
        Collections.sort(team1Sums);
        Collections.sort(team2Sums);

        int winCount = 0;
        int idxTeam2 = 0;
        int totalTeam2Counts = 0;

        // 승리 횟수 계산
        for (int sum1 : team1Sums) {
            int count1 = team1SumCounts.get(sum1);

            // 팀 2의 합계 중 팀 1의 현재 합계보다 작은 합계의 빈도 누적
            while (idxTeam2 < team2Sums.size() && team2Sums.get(idxTeam2) < sum1) {
                int sum2 = team2Sums.get(idxTeam2);
                int count2 = team2SumCounts.get(sum2);
                totalTeam2Counts += count2;
                idxTeam2++;
            }

            int winCountForSum1 = count1 * totalTeam2Counts;
            winCount += winCountForSum1;
        }

        return winCount;
    }

    // 합의 빈도를 계산하는 메서드 (합계 계산 과정 출력 포함)
    private static Map<Integer, Integer> generateSumCounts(List<int[]> team) {
        Map<Integer, Integer> sumCounts = new HashMap<>();
        sumCounts.put(0, 1);

        int diceNumber = 1;

        for (int[] dice : team) {
            Map<Integer, Integer> newSumCounts = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : sumCounts.entrySet()) {
                int currentSum = entry.getKey();
                int currentCount = entry.getValue();
                for (int face : dice) {
                    int newSum = currentSum + face;
                    newSumCounts.put(newSum, newSumCounts.getOrDefault(newSum, 0) + currentCount);
                }
            }
            sumCounts = newSumCounts;
            diceNumber++;
        }

        return sumCounts;
    }

}
* */