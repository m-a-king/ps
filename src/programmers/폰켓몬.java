package programmers;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class 폰켓몬 {

    class Solution {
        public int solution(int[] nums) {
            int answer = 0;

            int arrlength = nums.length;

            // nums 배열을 스트림으로 변환하고, boxed()로 IntStream을 Stream<Integer>로 변환한 후, Set으로 수집
            Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

            int setLength = set.size();

            answer = Math.min(arrlength / 2, setLength);
            return answer;
        }
    }
}
