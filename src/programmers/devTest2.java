package programmers;

import java.util.PriorityQueue;

public class devTest2 {

    static class Solution {

        public static void main(String[] args) {
            int k = 300;
            int[] limits = {2000, 1000, 3000, 200, 600, 500};
            int[][] sockets = new int[][]{
                    {2, 3, -1, -1, -1},
                    {4, 0, -1, -1, 6},
                    {5, 0, 0, 0, 0},
                    {-1, 0, 0, 0, 0},
                    {-1, -1, -1, -1, -1},
                    {-1, -1, 0, 0, 0}};

            int answer = Solution.solution(k, limits, sockets);
            System.out.println(answer);
        }

        static class LimitInfo implements Comparable<LimitInfo> {
            int idx, limit;

            LimitInfo(int idx, int limit) {
                this.idx = idx;
                this.limit = limit;
            }

            @Override
            public int compareTo(LimitInfo o) {
                return this.limit - o.limit; // 오름차순
            }
        }

        static int[][] sockets;
        static int[] limits;
        static int w;

        public static int solution(int k, int[] l, int[][] s) {
            int answer = 0;

            sockets = s;
            limits = l;
            w = k;

            updatePowerLimit();

            PriorityQueue<LimitInfo> pq = new PriorityQueue<>();
            for (int i = 0; i < limits.length; i++) {
                pq.offer(new LimitInfo(i + 1, limits[i])); // limits[i] -> (i+1) socketLimit
            }

            while (!pq.isEmpty()) {
                LimitInfo cLimitInfo = pq.poll();
                int cIdx = cLimitInfo.idx;
                int cLimit = cLimitInfo.limit;

                int cTotalPower = calcPower(cIdx);
                int overflow = cTotalPower - cLimit;

                if (overflow > 0) {
                    answer += unplug(cIdx, overflow);
                }


            }

            return answer;
        }

        private static int unplug(int target, int overflow) {
            int count = 0;

            for (int i = 0; i < 5; i++) {
                int cIdx = sockets[target - 1][i];

                if (cIdx >= 2) {
                    if (overflow <= 0) {
                        break;
                    }
                    count += unplug(cIdx, overflow);
                    overflow -= w * count;
                }
            }

            for (int i = 0; i < 5; i++) {
                int cIdx = sockets[target - 1][i];

                if (cIdx == -1) {
                    if (overflow <= 0) {
                        break;
                    }
                    overflow -= w;
                    count++;
                    System.out.println((target - 1) + "," + i + "를 뻈음");
                    sockets[target - 1][i] = 0;
                }
            }

            return count;
        }

        // 부모보다 높은 허용 전력을 가지는 것을 수정
        private static void updatePowerLimit() {
            for (int i = 0; i < limits.length; i++) {
                for (int j = 0; j < 5; j++) {
                    int idx = sockets[i][j];

                    if (idx >= 2) {
                        // 자식은 부모보다 낮은 허용 전력을 가진다.
                        limits[idx - 1] = Math.min(limits[idx - 1], limits[i]);
                    }
                }
            }
        }

        private static int calcPower(int cIdx) {
            int total = 0;

            for (int idx : sockets[cIdx - 1]) { // n번째 소켓 = sockets[n-1]
                if (idx == -1) {
                    total += w;
                    continue;
                }
                if (idx >= 2) {
                    total += calcPower(idx);
                }
            }

            return total;
        }


    }
}