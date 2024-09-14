package test;

public class test3_p_3 {

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
                        answer[k++] = j + 1;
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

    static int winCountS = 0;

    private static int calcWin() {
        winCountS = 0;
        calcScores(0, 0, 0);

        return winCountS;
    }


    private static void calcScores(int diceIdx, int sum1, int sum2) {
        if (diceIdx == length) {
            if (sum1 > sum2) winCountS++;
            return;
        }

        for (int i = 0; i < 6; i++) {
            if (visited[diceIdx]) {
                calcScores(diceIdx + 1, sum1 + diceS[diceIdx][i], sum2);
            } else {
                calcScores(diceIdx + 1, sum1, sum2 + diceS[diceIdx][i]);
            }
        }
    }
    
}
