package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 상담원인원 {

    public static void main(String[] args) {
        int k=3;
        int n =5;
        int[][] reqs = new int[][]{{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}};
        System.out.println(solution(k, n, reqs));
    }
    static int minTotalWait = Integer.MAX_VALUE;
    static int[] consultants;
    static int maxConsultants;

    public static int solution(int k, int n, int[][] reqs) {
        maxConsultants = n - k + 1;
        consultants = new int[k + 1];

        int[][] wait = new int[k + 1][maxConsultants + 1];
        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] req : reqs) {
            adjList.get(req[2]).add(new int[]{req[0], req[1]});
        }

        // wait 배열 미리 계산
        for (int i = 1; i <= k; i++) {
            List<int[]> requests = adjList.get(i);

            for (int j = 1; j <= maxConsultants; j++) {
                PriorityQueue<Integer> pq = new PriorityQueue<>();
                int totalWait = 0;

                for (int[] req : requests) {
                    int currStart = req[0];
                    int currDuration = req[1];

                    if (pq.size() < j) {
                        pq.offer(currStart + currDuration);
                    } else {
                        int earliestEnd = pq.poll();
                        if (earliestEnd > currStart) {
                            int currWait = earliestEnd - currStart;
                            totalWait += currWait;
                            pq.offer(earliestEnd + currDuration);
                        } else {
                            pq.offer(currStart + currDuration);
                        }
                    }
                }
                wait[i][j] = totalWait;
            }
        }

        for (int i = 0; i < wait.length; i++) {
            System.out.println(Arrays.toString(wait[i]));
        }

        dfs(1, n, k, wait);

        return minTotalWait;
    }



    public static void dfs(int idx, int remainingConsultants, int k, int[][] wait) {
        if (idx > k) {
            if (remainingConsultants == 0) {
                int totalWait = 0;
                System.out.println(Arrays.toString(consultants));
                for (int i = 1; i <= k; i++) {
                    totalWait += wait[i][consultants[i]];
                }
                minTotalWait = Math.min(minTotalWait, totalWait);
            }
            return;
        }

        for (int i = 1; i <= remainingConsultants; i++) {
            consultants[idx] = i;
            System.out.println("idx = " + idx);
            System.out.println("i = " + i);
            dfs(idx + 1, remainingConsultants - i, k, wait);
            System.out.println("imout");
            consultants[idx] = 0;
        }
    }
}
