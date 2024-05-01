package programmers;


import java.util.Comparator;
import java.util.PriorityQueue;

public class 프로세스우선순위큐 {

    public static int solution(int[] priorities, int location) {
        int answer = 0;

        // 내림차순으로 정렬되도록 Comparator.reverseOrder()를 사용 (기본적으로 오름차순)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < priorities.length; i++) {
            pq.add(priorities[i]);
        }


        while (!pq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                // pq의 front가 입력 배열의 몇 번째 요소인지 찾아냄
                // 그 다음 반복은 해당 idx부터 반복 (반복문 break 없으니, 당연한 것)
                if (pq.peek() == priorities[i]) {
                    // 찾아냈다면 순위 갱신 (몇 번째로 실행되는지)
                    answer++;

                    // 해당 요소가 문제의 요구 사항이라면?
                    if (i == location) {
                        // 정답 반환
                        return answer;
                    }
                    // 해당 요소가 문제의 요구 사항이 아니라면?
                    else {
                        // dequeue 하고 다시 for 문으로 이동
                        pq.poll();
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] priorities = {1, 1, 9, 1, 1, 1};
        System.out.println(solution(priorities, 1));
    }

}
