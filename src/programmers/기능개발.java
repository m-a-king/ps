package programmers;

import java.util.ArrayList;
import java.util.List;

public class 기능개발 {

    class Solution {
        public int[] solution(int[] progresses, int[] speeds) {
            List<Integer> answerList = new ArrayList<>();
            int count = 0;

            while (count != progresses.length) {

                for (int i = count; i < progresses.length; i++) {
                    progresses[i] += speeds[i];
                }

                int currentCount = 0;

                while (true) {
                    if (count < progresses.length && progresses[count] >= 100) {
                        count++;
                        currentCount++;
                    } else {
                        break;
                    }
                }

                if (currentCount > 0) {
                    answerList.add(currentCount);
                }

            }

            return answerList.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
