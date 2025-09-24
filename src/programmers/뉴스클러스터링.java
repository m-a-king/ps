package programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 뉴스클러스터링 {

    public int solution(String str1, String str2) {
        int answer = 0;

        Map<String, Integer> count1 = new HashMap<>();
        Map<String, Integer> count2 = new HashMap<>();

        for (int i = 0; i < str1.length() - 1; i++) {
            char c1 = str1.charAt(i);
            if (!Character.isLetter(c1)) {
                continue;
            }
            char c2 = str1.charAt(i + 1);
            if (!Character.isLetter(c2)) {
                continue;
            }

            count1.merge(new String(new char[]{c1, c2}).toLowerCase(), 1, Integer::sum);
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            char c1 = str2.charAt(i);
            if (!Character.isLetter(c1)) {
                continue;
            }
            char c2 = str2.charAt(i + 1);
            if (!Character.isLetter(c2)) {
                continue;
            }

            count2.merge(new String(new char[]{c1, c2}).toLowerCase(), 1, Integer::sum);
        }

        Set<String> unionKeys = new HashSet<>();
        unionKeys.addAll(count1.keySet());
        unionKeys.addAll(count2.keySet());
        int acc = 0;
        int overlap = 0;

        for (String key : unionKeys) {
            int c1 = count1.getOrDefault(key, 0);
            int c2 = count2.getOrDefault(key, 0);

            acc += Math.max(c1, c2);
            overlap += Math.min(c1, c2);
        }

        if (acc == 0) {
            return 65536;
        }

        return 65536 * overlap / acc;
    }
}
