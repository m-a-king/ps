package programmers;

import java.util.HashSet;
import java.util.Set;

public class 연속부분수열합의개수 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{7, 9, 1, 1, 4}));

    }

    static class Solution {
        public int solution(int[] input) {
            int answer = 0;
            Set<Integer> set = new HashSet<>();

            for (int targetDepth = 1; targetDepth <= input.length; targetDepth++) {

                for (int start = 0; start < input.length; start++) {
                    int value = 0;
                    for (int end = start; end < start + targetDepth; end++) {
                        value += input[end % input.length];
                    }
                    set.add(value);
                }

            }

            System.out.println(set);
            System.out.println(set.size());

            return answer;
        }
    }
}
