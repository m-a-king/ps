package programmers;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Stack;

public class 주식가격_2 {


    static class Solution {

        public static void main(String[] args) {

            int[] answer = solution(new int[]{1, 2, 3, 2, 3});

        }

        public static int[] solution(int[] prices) {
            int[] answer = new int[prices.length];

            Stack<Integer> stack = new Stack<>();
            stack.push(0);

            for (int i = 1; i < answer.length; i++) {
                while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                    Integer pop = stack.pop();
                    answer[pop] = i - pop;

                }

                stack.push(i);

            }

            while (!stack.isEmpty()) {
                int pop = stack.pop();

                answer[pop] = answer.length - 1 - pop;
            }

            return answer;
        }
    }
}


// 1 2 3 2 3
// 0 1 3 4
// 0 0 1 0 0