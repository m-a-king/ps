package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class 귤고르기 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(6, new int[]{1, 3, 2, 5, 4, 5, 2, 3}));

    }

    static class Solution {
        public int solution(int k, int[] tangerine) {
            int answer = 0;

            Map<Integer, Integer> map = new HashMap<>();

            for(int t: tangerine) {
                map.put(t, map.getOrDefault(t, 0) + 1);
            }

            final ArrayList<Integer> values = new ArrayList<>(map.values());
            values.sort(Comparator.reverseOrder());

            System.out.println(values);

            for (int count : values) {
                System.out.println("count = " + count);
                System.out.println("k = " + k);
                if (k - count >= 0) {
                    answer++;
                    k -= count;
                } else {
                    break;
                }
            }

            return answer;
        }
    }
}
