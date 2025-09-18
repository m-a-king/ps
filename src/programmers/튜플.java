package programmers;

import java.util.*;

class 튜플 {
    public int[] solution(String s) {
        int[] answer = {};

        Map<String, Integer> count = new HashMap<>();

        String curr = "";
        for(char c: s.toCharArray()){
            if(Character.isDigit(c)){
                curr += String.valueOf(c);
                continue;
            }

            if(curr.length() == 0) continue;
            count.merge(curr, 1, Integer::sum);
            curr = "";
        }

        return count.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(e-> e.getKey())
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
