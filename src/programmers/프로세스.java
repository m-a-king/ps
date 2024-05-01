package programmers;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class 프로세스 {

    static class Solution {

        private static class Process {
            int priority, idx;

            public Process(int priority, int idx) {
                this.priority = priority;
                this.idx = idx;
            }

        }

        public int solution(int[] priorities, int location) {
            int answer = 0;

            Queue<Process> queue = new ArrayDeque<>();

            int idx = 0;
            for (int priority : priorities) {
                queue.offer(new Process(priority, idx++));
            }

            while (!queue.isEmpty()) {
                Process current = queue.poll();

                if (hasHigherPriority(queue, current.priority)) {
                    queue.offer(current);
                } else {
                    answer++;
                    if (current.idx == location) {
                        return answer;
                    }
                }
            }
            return answer;
        }

        private boolean hasHigherPriority(Queue<Process> queue, int current) {

            for (Process process : queue) {
                if (process.priority > current) {
                    return true;
                }
            }
            return false;
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 테스트 케이스
        int[] priorities = {1, 1, 9, 1, 1, 1};
        int location = 1;

        int result = solution.solution(priorities, location);
        System.out.println("Result: " + result); // 예상 결과: 1
    }
}
