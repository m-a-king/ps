package programmers;

import java.util.HashMap;
import java.util.Map;

public class 가장많이받은선물 {

    public static void main(String[] args) {
        String[] f = {"muzi", "ryan", "frodo", "neo"};
        String[] g = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
        System.out.println(solution(f, g));
    }

    public static int solution(String[] friends, String[] gifts) {
        int answer = 0;

        Map<String, Integer> fMap = new HashMap<>();
        Map<String, Integer> gMap = new HashMap<>();

        for (String friend1 : friends) {
            fMap.put(friend1, 0);
            for (String friend2 : friends) {
                if (friend1.equals(friend2)) continue;
                gMap.put(friend1 + " " + friend2, 0);
            }
        }

        System.out.println("gMap = " + gMap.keySet().size());
        System.out.println(gMap.keySet().toString());

        for (String gift : gifts) {
            String[] fromTo = gift.split(" ");
            String from = fromTo[0];
            String to = fromTo[1];
            fMap.put(from, fMap.get(from) + 1);
            fMap.put(to, fMap.get(to) - 1);
            gMap.put(gift, gMap.get(gift) + 1);
        }

        for (String friend1 : friends) {
            int giftPoint1 = fMap.get(friend1);
            int nextGift = 0;

            for (String friend2 : friends) {
                if (friend1.equals(friend2)) continue;
                int giftPoint2 = fMap.get(friend2);

                int give = gMap.get(friend1 + " " + friend2);
                int take = gMap.get(friend2 + " " + friend1);
                if ((give == take && giftPoint1 > giftPoint2) || give > take) {
                    nextGift++;
                }

            }

            answer = Math.max(answer, nextGift);
        }


        return answer;
    }
}
