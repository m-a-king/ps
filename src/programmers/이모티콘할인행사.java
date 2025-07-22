package programmers;

import java.util.Arrays;

public class 이모티콘할인행사 {

    public static void main(String[] args) {
        int[][] users = new int[][]{{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        int[] emoticons = new int[]{1300, 1500, 1600, 4900};
        System.out.println(Arrays.toString(new Solution().solution(users, emoticons)));
    }

    static class Solution {
        private static final int[] rates = {40, 30, 20, 10};
        private static int[] selected;
        private static int[][] users;
        private static int[] emoticons;
        private static int[] temp = {0, 0};
        private static int[] answer = {0, 0};

        public int[] solution(int[][] input1, int[] input2) {
            users = input1;
            emoticons = input2;
            selected = new int[emoticons.length];

            dfs(0);

            return answer;
        }

        private void dfs(int depth) {

            if (depth == selected.length) {
                System.out.println(Arrays.toString(selected));
                calc();
                System.out.println("temp: " + Arrays.toString(temp));
                if (temp[0] > answer[0]) {
                    answer[0] = temp[0];
                    answer[1] = temp[1];
                }
                if (temp[0] == answer[0] && temp[1] > answer[1]) {
                    answer[1] = temp[1];
                }
                temp = new int[]{0, 0};
                return;
            }

            for (int i = 0; i < rates.length; i++) {
                temp = new int[]{0, 0};
                selected[depth] = rates[i];
                dfs(depth + 1);

            }
        }

        private void calc() {
            for (int[] user : users) {
                int currAcc = 0;
                for (int i = 0; i < emoticons.length; i++) {
                    if (user[0] > selected[i]) {
                        continue;
                    }
                    currAcc += emoticons[i] * (100 - selected[i]) / 100;
                }

                if (currAcc >= user[1]) {
                    System.out.println("asd");
                    temp[0]++;
                    continue;
                }
                temp[1] += currAcc;
            }
        }
    }
}
