package programmers;

import java.util.Arrays;
import java.util.Stack;

public class 뒤에있는큰수찾기 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new int[]{9, 1, 5, 3, 6, 2})));
    }

    static class Solution {
        public int[] solution(int[] numbers) {
            int[] answer = new int[numbers.length];
            answer[numbers.length - 1] = -1;

            Stack<Integer> stack = new Stack<>();
            stack.push(numbers[numbers.length - 1]);

            for (int i = numbers.length - 2; i >= 0; i--) {
                if (!stack.isEmpty() && stack.peek() > numbers[i]) {
                    answer[i] = stack.peek();
                    stack.push(numbers[i]);
                    continue;
                }

                while (!stack.isEmpty() && stack.peek() <= numbers[i]) {
                    stack.pop();
                }
                answer[i] = stack.isEmpty()? -1 : stack.peek();
                stack.push(numbers[i]);
            }

            return answer;
        }
    }
}
