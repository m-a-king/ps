package programmers;

import java.util.*;

public class 이중우선순위큐 {

    static class Solution {

        public static void main(String[] args) {
            solution(new String[]{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"});
        }

        static public int[] solution(String[] operations) {
            int[] answer = {};

            // 최소힙
            PriorityQueue<Integer> minPQ = new PriorityQueue<>();
            // 최대힙
            PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
            // 해시맵 <데이터 값, 데이터 개수>
            HashMap<Integer, Integer> inputHMap = new HashMap<>();

            // 각 입력 연산을 처리
            for (String operation : operations) {

                // I, D 를 구분
                char command = operation.charAt(0);
                int data = 0;

                // data 의 부호 처리
                char sign = '+';
                char signCheck = operation.charAt(2);
                if (signCheck == '-') {
                    sign = '-';
                } else {
                    // '-' 기호가 없다면 바로 숫자이므로 예외 처리
                    // char 값에 '0' 을 빼면 올바른 int 값을 얻을 수 있음.
                    data = signCheck - '0';
                    //Character.getNumericValue()
                }

                // 남은 데이터를 읽음
                for (int i = 3; i < operation.length(); i++) {
                    data *= 10;
                    data += operation.charAt(i) - '0';
                    //Character.getNumericValue()
                }

                // 부호 처리
                if (sign == '-') {
                    data *= -1;
                }

                // 입력일 경우
                if (command == 'I') {
                    // 각 pq 에 넣어주고, 해시맵에도 넣어줌
                    minPQ.offer(data);
                    maxPQ.offer(data);
                    inputHMap.put(data, inputHMap.getOrDefault(data, 0) + 1);
                }
                // 삭제일 경우 (else)
                else if (command == 'D') {
                    if (data == 1) {
                        // 최대값 삭제
                        removeInHM(maxPQ, inputHMap);
                    } else if (data == -1) {
                        // 최소값 삭제
                        removeInHM(minPQ, inputHMap);
                    }
                }
            }

            int min = checkValidValue(minPQ, inputHMap);
            int max = checkValidValue(maxPQ, inputHMap);

            for (Map.Entry<Integer, Integer> entry : inputHMap.entrySet()) {
                System.out.println(entry);
            }

            System.out.println(max);
            System.out.println(min);

            answer = new int[]{max, min};

            return answer;
        }

        private static int checkValidValue(PriorityQueue<Integer> pq, HashMap<Integer, Integer> inputHMap) {
            while (!pq.isEmpty()) {
                // 해당 pq의 루트 노드를 체크
                int peek = pq.peek();

                // 그 값의 개수가 0개 초과인지 해시맵에서 체크
                if (inputHMap.get(peek) > 0) {
                    return peek;
                }
                // 0개 이하라면 dequeue, 다시 위 과정 진행
                pq.poll();
            }

            // 해당 pq가 비었다면 -> 유효한 값이 하나도 없다 -> 0 반환
            return 0;
        }

        private static void removeInHM(PriorityQueue<Integer> pq, HashMap<Integer, Integer> inputHMap) {
            while (!pq.isEmpty()) {
                // 해당 pq 를 dequeue
                int poll = pq.poll();

                // dequeue 값이 해시맵에서 확인했을 떄, 1개 이상이라면 1을 뺀다.
                // 1개 이상이라는 것은 유효한 데이터라는 것, 1개 이상이 아니였다면 이는 이미 삭제된 값이므로 반복해야함.
                // 0개 일때는 빼면 안됨! -> 무시해야하는 연산이므로..
                if (inputHMap.get(poll) > 0) {
                    inputHMap.put(poll, inputHMap.get(poll) - 1);
                    break;
                }
            }
        }
    }
}
