package programmers;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

class 캐캐시 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if(cacheSize == 0) return cities.length * 5;

        Set<String> cache = new LinkedHashSet<>();

        for(String raw: cities) {
            String city = raw.toLowerCase();

            if(cache.contains(city)){
                answer++;
                cache.remove(city);
                cache.add(city);
                continue;
            }

            answer+=5;
            if (cache.size() == cacheSize) {
                Iterator<String> it = cache.iterator();
                if (it.hasNext()) {
                    it.next();
                    it.remove();
                }
            }
            cache.add(city);
        }

        return answer;
    }
}
