package programmers;

public class 서버증설횟수 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] players = new int[]{0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};
        int m = 3;
        int k = 5;

        System.out.println("answer = " + solution.solution(players, m, k));
    }

    static class Solution {

        public int solution(int[] players, int m, int k) {
            int answer = 0;

            int[] needServerCount = new int[players.length];

            for (int i = 0; i < players.length; i++) {
                needServerCount[i] = players[i] / m;
//                needServerCount[i] += players[i] % m == 0 ? 0 : 1;
            }

            for (int count : needServerCount) {
                System.out.print(count + " ");
            }

            for (int i = 0; i < needServerCount.length; i++) {

                System.out.println();

                for (int count : needServerCount) {
                    System.out.print(count + " ");
                }

                if (needServerCount[i] <= 0) {
                    continue;
                }

                answer += needServerCount[i];
                for (int j = i + 1; j < Math.min(needServerCount.length, i + k); j++) {
                    needServerCount[j] -= needServerCount[i];
                }
            }

            return answer;
        }
    }

}
