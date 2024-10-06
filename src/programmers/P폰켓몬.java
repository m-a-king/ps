package programmers;

import java.util.HashSet;
import java.util.Set;

public class P폰켓몬 {

    class Solution {
        public int solution(int[] nums) {
            int answer = 0;

            Set<Integer> set = new HashSet<>();
            for(int n:nums){
                set.add(n);
            }

            if(set.size() > nums.length / 2){
                answer = nums.length / 2;
            }else{
                answer = set.size();
            }

            return answer;
        }
    }
}
