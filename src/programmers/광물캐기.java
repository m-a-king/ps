package programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class 광물캐기 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{1, 0, 0},
                new String[]{"iron", "iron", "iron", "iron", "iron", "diamond"}));
    }

    static class Solution {
        public int solution(int[] picks, String[] minerals) {
            int answer = 0;

            int diaPick = picks[0];
            int ironPick = picks[1];
            int stonePick = picks[2];

            System.out.println("diaPick = " + diaPick);
            System.out.println("ironPick = " + ironPick);
            System.out.println("stonePick = " + stonePick);

            int maxGroup = diaPick + ironPick + stonePick;
            int groupCount = Math.min(maxGroup,
                    minerals.length / 5 + (minerals.length % 5 == 0 ? 0 : 1));
            List<Group> groups = new ArrayList<>();
            for (int i = 0; i < groupCount; i++) {
                groups.add(new Group(0, 0, 0));
            }

            for (int i = 0; i < Math.min(minerals.length, maxGroup * 5); i++) {
                if (minerals[i].startsWith("d")) {
                    groups.get(i / 5).diaCount++;
                } else if (minerals[i].startsWith("i")) {
                    groups.get(i / 5).ironCount++;
                } else {
                    groups.get(i / 5).stoneCount++;
                }
            }

            PriorityQueue<Group> pq = new PriorityQueue<>(groups);
            System.out.println(pq);

            while (!pq.isEmpty()) {
                Group group = pq.poll();
                if (group.countTotal() != 5 && pq.size() == 1) {
                    group = pq.poll();
                }

                final String bestPick = getBestPick(picks);
                if (bestPick.equals("STOP")) {
                    return answer;
                }
                System.out.println("bestPick = " + bestPick);

                answer += group.calcStress(bestPick);
            }

            System.out.println(answer);

            return answer;
        }

        private String getBestPick(int[] picks) {
            if (picks[0] > 0) {
                picks[0]--;
                return "d";
            }
            if (picks[1] > 0) {
                picks[1]--;
                return "i";
            }
            if (picks[2] > 0) {
                picks[2]--;
                return "s";
            }
            return "STOP";
        }

        static class Group implements Comparable<Group> {
            int stoneCount;
            int ironCount;
            int diaCount;

            public Group(int stoneCount, int ironCount, int diaCount) {
                this.stoneCount = stoneCount;
                this.ironCount = ironCount;
                this.diaCount = diaCount;
            }

            public int calcStress(String pick) {
                if (pick.equals("d")) {
                    return countTotal();
                }

                if (pick.equals("i")) {
                    return diaCount * 5 + ironCount + stoneCount;
                }

                if (pick.equals("s")) {
                    return diaCount * 25 + ironCount * 5 + stoneCount;
                }

                return -1;
            }

            private int calcWeight() {
                return stoneCount + ironCount * 5 + diaCount * 25;
            }

            private int countTotal() {
                return stoneCount + ironCount + diaCount;
            }

            @Override
            public int compareTo(Group o) {
                int diff = calcWeight() - o.calcWeight();
                if (diff == 0) {
                    return countTotal() - o.countTotal();
                }
                return diff * -1;
            }

            @Override
            public String toString() {
                return "Group{" + "stoneCount=" + stoneCount + ", ironCount=" + ironCount + ", diaCount=" + diaCount
                        + '}';
            }
        }
    }
}
