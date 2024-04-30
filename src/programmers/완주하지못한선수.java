package programmers;

import java.util.HashMap;

public class 완주하지못한선수 {

    class Solution {
        public String solution(String[] participants, String[] completions) {
            String answer = "";

            HashMap<String, Integer> pMap = new HashMap<>();

            for (String participant : participants) {
                pMap.put(participant, pMap.getOrDefault(participant, 0) + 1);
            }

            for (String completion : completions) {
                pMap.put(completion, pMap.get(completion) - 1);
            }

            for (String pKey : pMap.keySet()) {
                if (pMap.get(pKey) != 0) {
                    answer = pKey;
                }
            }
            return answer;
        }
    }
}
