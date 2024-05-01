package programmers;

import java.util.ArrayList;
import java.util.List;

public class 같은숫자는싫어 {


    public class Solution {
        public int[] solution(int[] arr) {

            int[] answer = {};

            int recent = -1;
            List<Integer> answerList = new ArrayList<>();

            for (int num : arr) {
                if (num != recent) {
                    answerList.add(num);
                    recent = num;
                }
            }

            answer = answerList.stream().mapToInt(Integer::intValue).toArray();

            return answer;

        }
    }
}
