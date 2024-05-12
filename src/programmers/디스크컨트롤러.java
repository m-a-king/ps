package programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 디스크컨트롤러 {

    static class Solution {

        public static void main(String[] args) {
            System.out.println(solution(new int[][]{{0, 3}, {1, 9}, {2, 6}}));

        }

        private static class Job implements Comparable<Job> {
            int startTime, processingTime;

            public Job(int startTime, int processingTime) {
                this.startTime = startTime;
                this.processingTime = processingTime;
            }

            @Override
            public int compareTo(Job o) {

                return this.processingTime < o.processingTime ? -1 : 1;
            }

        }

        public static int solution(int[][] jobs) {
            int answer = 0;

            PriorityQueue<Job> pq = new PriorityQueue<>();
            Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

            int currentTime = 0;
            int jobIdx = 0;
            int completeCount = 0;

            while (completeCount != jobs.length) {
                while (jobIdx < jobs.length && jobs[jobIdx][0] <= currentTime) {
                    pq.offer(new Job(jobs[jobIdx][0], jobs[jobIdx][1]));
                    jobIdx++;
                }
                if (pq.isEmpty()) {
                    currentTime = jobs[jobIdx][0];
                } else {
                    Job now = pq.poll();
                    completeCount++;
                    currentTime += now.processingTime;
                    answer += currentTime - now.startTime;
                    System.out.println("answer = " + answer);
                    System.out.println("currentTime = " + currentTime);


                }
            }

            answer /= jobs.length;

            return answer;
        }
    }

}
