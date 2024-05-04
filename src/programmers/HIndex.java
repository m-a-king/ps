package programmers;

import java.util.Arrays;

public class HIndex {


    static class Solution {

        public static void main(String[] args) {
            solution(new int[]{3,5,11,6,1,5,3,3,1,41});
        }
        public static int solution(int[] citations) {
            int answer = 0;
            int citLength = citations.length;

            Arrays.sort(citations); // 0 1 3 5 6
            // h회 이상 인용 -> h개

            for (int i = 0; i < citations.length; i++) {
                int orMore = citations.length - i;

                if (citations[i] >= orMore) {
                    answer = Math.min(citations[i], orMore);
                    return answer;
                }
            }


            return answer;
        }
    }
}
