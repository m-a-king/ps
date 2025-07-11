package programmers;

import java.util.Arrays;

public class 연속부분수열의합 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        final int[] seq = {2, 2, 2, 2, 2};
        int k = 6;
        System.out.println(Arrays.toString(solution.solution(seq, k)));
    }

    static class Solution {
        public int[] solution(int[] sequence, int k) {
            int[] answer = new int[2];

            int acc = 0;
            int left = 0;
            int minLength = Integer.MAX_VALUE;

            for (int right = 0; right < sequence.length; right++) {
                acc += sequence[right];

                while (acc >= k) {
                    if (acc == k && right - left < minLength) {
                        minLength = right - left;
                        answer[0] = left;
                        answer[1] = right;
                    }
                    acc -= sequence[left++];
                }
            }

            return answer;
        }
    }
}
