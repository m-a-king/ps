package programmers;

import java.util.PriorityQueue;

public class 더맵게 {

    class Solution {
        public int solution(int[] scoville, int K) {
            int answer = 0;

            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for (int sco : scoville) {
                pq.offer(sco);
            }

            if (pq.peek() >= K) {
                return 0;
            }


            int count = 0;

            while (pq.size() > 1) {
                int first = pq.poll();
                int second = pq.poll();
                int now = first + second * 2;
                count++;
                if (now >= K) {
                    if (pq.isEmpty() || pq.peek() >= K) {
                        return count;
                    }
                }
                pq.offer(now);
            }


            return -1;
        }

    }

}
