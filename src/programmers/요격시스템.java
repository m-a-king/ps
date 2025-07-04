package programmers;

import java.util.Arrays;

public class 요격시스템 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] target = new int[][]{{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}};
        System.out.println(solution.solution(target));
    }

    static class Solution {
        public int solution(int[][] targets) {
            int answer = 0;

            int last = Integer.MAX_VALUE;

            final int[][] sorted = Arrays.stream(targets).sorted((a, b) -> b[0] - a[0]).toArray(int[][]::new);

            System.out.println(Arrays.deepToString(sorted));
            for (int[] pos : sorted) {
                int start = pos[0];
                int end = pos[1];

                if (start <= last && last < end) {
                    continue;
                }

                last = start;
                System.out.println(last);
                answer++;

            }

            return answer;
        }
    }

}
