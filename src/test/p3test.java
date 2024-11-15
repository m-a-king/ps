import java.util.*;

public class p3test {

    private static class Job {
        int idx, start, processTime;

        public Job(int idx, int start, int processTime) {
            this.idx = idx;
            this.start = start;
            this.processTime = processTime;
        }

        public int getProcessTime() {
            return this.processTime;
        }
    }

    public int solution(int[][] jobs) {
        PriorityQueue<Job> jobQueue = new PriorityQueue<>(Comparator.comparingInt(j -> j.start));
        PriorityQueue<Job> processQueue = new PriorityQueue<>(Comparator.comparingInt(Job::getProcessTime));

        for (int i = 0; i < jobs.length; i++) {
            int start = jobs[i][0];
            int duration = jobs[i][1];
            jobQueue.add(new Job(i, start, duration));
        }

        int currentTime = 0;
        int totalProcessTime = 0;

        while (!jobQueue.isEmpty() || !processQueue.isEmpty()) {
            while (!jobQueue.isEmpty() && jobQueue.peek().start <= currentTime) {
                processQueue.add(jobQueue.poll());
            }

            if (processQueue.isEmpty()) {
                if (jobQueue.isEmpty()) continue;
                currentTime = jobQueue.peek().start;
            } else {
                Job currentJob = processQueue.poll();
                currentTime += currentJob.getProcessTime();
                totalProcessTime += currentTime - currentJob.start;
            }
        }

        return totalProcessTime / jobs.length;
    }

    public static void main(String[] args) {
        p3test test = new p3test();
        int[][] jobs = {{0, 3}, {1, 9}, {3, 5}};
        System.out.println("Average: " + test.solution(jobs));
    }
}