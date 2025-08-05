package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 할인행사 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                new String[]{"banana", "apple", "rice", "pork", "pot"},
                new int[]{3, 2, 2, 2, 1},
                new String[]{"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice",
                        "pot", "banana", "apple", "banana"}));

    }


    static class Solution {
        static int answer = 0;

        public int solution(String[] want, int[] number, String[] discount) {
            System.out.println(Arrays.toString(number));
            System.out.println();
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < want.length; i++) {
                map.put(want[i], i);
            }

            System.out.println(map);

            int[] arr = new int[number.length];

            int left = 0;
            int right = 0;

            while (right < discount.length) {
                System.out.println(Arrays.toString(arr));
                System.out.println("left = " + left);
                System.out.println("right = " + right);
                System.out.println();
                if (map.get(discount[right]) == null) {
                    right++;
                    left = right;
                    arr = new int[number.length];
                    continue;
                }

                arr[map.get(discount[right])]++;
                right++;

                if (right - left == 10) {
                    check(number, arr);
                    arr[map.get(discount[left])]--;
                    left++;
                }
            }

            return answer;
        }

        private void check(int[] number, int[] arr) {
            for (int i = 0; i < number.length; i++) {
                if (number[i] != arr[i]) {
                    return;
                }
            }
            answer++;
        }
    }
}
