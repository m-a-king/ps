package programmers;

import java.util.Stack;

public class 주식가격 {


    static class Solution {

        public static void main(String[] args) {

            int[] answer = solution(new int[]{1, 2, 3, 2, 3});

        }

        static public int[] solution(int[] prices) {
            int[] answer = new int[prices.length];

            for (int i = 0; i < answer.length - 1; i++) {
                for (int j = i + 1; j < answer.length; j++) {
                    answer[i]++;

                    if (prices[i] > prices[j]) {
                        break;
                    }
                }
            }

            for (int a : answer) {
                System.out.print(a + " ");
            }
            return answer;
        }
    }
}