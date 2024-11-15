package test;

import java.util.*;

public class p3test2 {

    class Solution {
        private static class Job implements Comparable<Job> {
            int idx, start, processTime;

            public Job(int idx, int start, int processTime) {
                this.idx = idx;
                this.start = start;
                this.processTime = processTime;
            }

            public int getProcessTime() {
                return this.processTime;
            }

            @Override
            public int compareTo(Job o) {
                if (this.start == o.start) {
                    if (this.getProcessTime() == o.getProcessTime()) {
                        return this.idx - o.idx;
                    }
                    return this.getProcessTime() - o.getProcessTime();
                }
                return this.start - o.start;
            }
        }

        public int solution(int[][] jobs) {
            List<Job> jobList = new ArrayList<>();
            for (int i = 0; i < jobs.length; i++) {
                int start = jobs[i][0];
                int processTime = jobs[i][1];
                jobList.add(new Job(i, start, processTime));
            }

            Collections.sort(jobList);

            int currentTime = 0;
            int totalProcessTime = 0;
            PriorityQueue<Job> pq = new PriorityQueue<>(Comparator.comparingInt(Job::getProcessTime));

            int i = 0;
            while (i < jobList.size() || !pq.isEmpty()) {
                while (i < jobList.size() && jobList.get(i).start <= currentTime) {
                    pq.add(jobList.get(i));
                    i++;
                }

                if (pq.isEmpty()) {
                    currentTime = jobList.get(i).start;
                } else {
                    Job currentJob = pq.poll();
                    currentTime += currentJob.getProcessTime();
                    totalProcessTime += currentTime - currentJob.start;
                }
            }

            return totalProcessTime / jobs.length;
        }
    }
}