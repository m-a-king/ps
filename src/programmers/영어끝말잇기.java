package programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 영어끝말잇기 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().solution(
                3, new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"})));

        System.out.println(Arrays.toString(new Solution().solution(
                5, new String[]{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure",
                        "establish", "hang", "gather", "refer", "reference", "estimate", "executive"})));

        System.out.println(Arrays.toString(new Solution().solution(
                2, new String[]{"hello", "one", "even", "never", "now", "world", "draw"})));

        System.out.println(Arrays.toString(new Solution().solution(
                3, new String[]{"abc", "cbd", "ddd", "ddd", "dbc", "cbd"})));
    }

    static class Solution {
        public int[] solution(int n, String[] words) {
            Set<String> set = new HashSet<>();
            set.add(words[0]);

            for (int i = 1; i < words.length; i++) {
                if (set.contains(words[i])) {
                    return new int[]{(i % n) + 1, (i / n) + 1};
                }
                set.add(words[i]);

                if (words[i - 1].charAt(words[i - 1].length() - 1) != words[i].charAt(0)) {
                    return new int[]{(i % n) + 1, (i / n) + 1};
                }
            }

            return new int[]{0, 0};
        }
    }
}

