package programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class 프로세스 {

    static class Solution {

        // 해당 프로세스의 우선도와 입력순서를 저장하는 클래스를 정의
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

            // 큐에 각 요소들을 입력
            int idx = 0;
            for (int priority : priorities) {
                queue.offer(new Process(priority, idx++));
            }

            while (!queue.isEmpty()) {
                Process current = queue.poll();

                // 현재 꺼낸 프로세스보다 더 큰 프로세스가 있다면?
                if (hasHigherPriority(queue, current.priority)) {
                    // 꺼낸 프로세스를 다시 넣음
                    queue.offer(current);
                }

                // 현재 꺼낸 프로세스가 제일 큰 프로세스라면?
                else {
                    // 해당 프로세스를 (answer+1)순위로 꺼냄
                    answer++;
                    // 해당 프로세스가 문제에서 요구하는 답이라면?
                    // 해당 프로세스가 몇 번째 순위로 꺼내졌는지 반환
                    if (current.idx == location) {
                        return answer;
                    }
                }
            }
            return answer;
        }

        // 나보다 우선순위가 큰 프로세스가 있는지 검사하는 메서드
        private boolean hasHigherPriority(Queue<Process> queue, int current) {

            // 큐에서 방금 꺼낸 프로세스를 제외한 모든 프로세스를 검사
            for (Process process : queue) {
                // 우선순위가 더 높은 프로세스가 있다면 true 반환
                if (process.priority > current) {
                    return true;
                }
            }
            // 없다면 false 반환
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
