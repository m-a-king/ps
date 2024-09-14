package test;

import java.util.*;

public class test3_p_2 {

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

    private static void selectDice(int curr, int depth) {
        if (depth == length / 2) {

            int winCount = calcWin();

            if (winCount > maxWin) {
                maxWin = winCount;

                int k = 0;
                for (int j = 0; j < visited.length; j++) {
                    if (visited[j]) {
                        answer[k++] = j + 1; // 인덱스를 1부터 시작하도록 조정
                    }
                }
            }
            return;
        }

        for (int i = curr; i < length; i++) {
            visited[i] = true;
            selectDice(i + 1, depth + 1);
            visited[i] = false;
        }

    }

    private static int calcWin() {
        // 팀 1과 팀 2의 가능한 합계를 리스트로 생성
        List<Integer> team1Sums = generateSums(true);
        List<Integer> team2Sums = generateSums(false);

        // 팀 2의 합계를 정렬
        Collections.sort(team2Sums);

        // 승리 횟수 계산
        int winCount = 0;

        for (int sum1 : team1Sums) {
            // 팀 2의 합계 중 sum1보다 작은 값의 개수를 이분 탐색으로 찾음
            int count = lowerBound(team2Sums, sum1);
            winCount += count;
        }

        return winCount;
    }

    private static List<Integer> generateSums(boolean isTeam1) {
        List<int[]> diceList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (visited[i] == isTeam1) {
                diceList.add(diceS[i]);
            }
        }

        List<Integer> sums = new ArrayList<>();
        sums.add(0); // 초기 합계 0

        for (int[] dice : diceList) {
            List<Integer> newSums = new ArrayList<>();
            for (int sum : sums) {
                for (int face : dice) {
                    newSums.add(sum + face);
                }
            }
            sums = newSums;
        }

        return sums;
    }

    private static int lowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left; // target보다 작은 값의 개수
    }

    public static void main(String[] args) {
        int[][] input = {
                {1, 2, 3, 4, 5, 6},
                {3, 3, 3, 3, 4, 4},
                {1, 3, 3, 4, 4, 4},
                {1, 1, 4, 4, 5, 5}
        };
        int[] answer = solution(input);

        System.out.println("최대 승리 횟수를 가진 팀 구성:");
        for (int idx : answer) {
            System.out.println("주사위 인덱스: " + idx);
        }
        System.out.println("최대 승리 횟수: " + maxWin);
    }
}