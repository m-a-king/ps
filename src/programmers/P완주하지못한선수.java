package programmers;

import java.util.HashMap;
import java.util.Map;

public class P완주하지못한선수 {

    class Solution {
        public String solution(String[] participant, String[] completion) {
            Map<String, Integer> map = new HashMap<>();
            for(String p : participant){
                map.compute(p, (k,v) -> (v == null) ? 0 : v+1);
            }

            for(String c: completion){
                map.compute(c, (k,v) -> v-1);
            }

            for(Map.Entry<String, Integer> e : map.entrySet()){
                if(e.getValue() != 0) return e.getKey();
            }
            return "";
        }
    }
}
