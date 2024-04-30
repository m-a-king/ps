package programmers;

import java.util.HashMap;

public class 의상 {

    class Solution {
        public int solution(String[][] clothes) {
            int answer = 1;

            HashMap<String, Integer> map = new HashMap<>();

            // 옷을 맵에 저장합니다. 키는 옷의 종류, 값은 해당 종류의 옷의 수입니다.
            for (String[] cloth : clothes) {
                map.put(cloth[1], map.getOrDefault(cloth[1], 0) + 1);
            }

            // 각 옷의 종류별로 입지 않는 경우를 포함하여 모든 경우의 수를 곱합니다.
            for (int count : map.values()) {
                answer *= (count + 1);
            }

            // 아무것도 입지 않는 경우 하나를 제외합니다.
            return --answer;
        }
    }
}
