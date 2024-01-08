package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BJ7662 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = parseInt(bufferedReader.readLine());

        for (int tc = 0; tc < testCase; tc++) {
            PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> minPQ = new PriorityQueue<>();
            Map<Integer, Integer> checkMap = new HashMap<>();

            int n = parseInt(bufferedReader.readLine());

            for (int i = 0; i < n; i++) {
                String[] input = bufferedReader.readLine().split(" ");
                String command = input[0];
                int number = parseInt(input[1]);

                if (Objects.equals(command, "I")) {
                    maxPQ.add(number);
                    minPQ.add(number);
                    checkMap.put(number, checkMap.getOrDefault(number, 0) + 1);

                } else if (Objects.equals(command, "D")) {
                    if (number == 1) {
                        cleanQueue(maxPQ, checkMap);
                        if (!maxPQ.isEmpty()) {
                            int max = maxPQ.poll();
                            checkMap.put(max, checkMap.get(max) - 1);
                            if (checkMap.get(max) == 0) {
                                checkMap.remove(max);
                            }
                        }
                    } else if (number == -1) {
                        cleanQueue(minPQ, checkMap);
                        if (!minPQ.isEmpty()) {
                            int min = minPQ.poll();
                            checkMap.put(min, checkMap.getOrDefault(min, 0) - 1);
                            if (checkMap.get(min) == 0) {
                                checkMap.remove(min);
                            }
                        }
                    }
                }
            }

            cleanQueue(maxPQ, checkMap);
            cleanQueue(minPQ, checkMap);

            if (maxPQ.isEmpty() || minPQ.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(maxPQ.peek() + " " + minPQ.peek());
            }
        }
    }

    // 지연 삭제 함수
    private static void cleanQueue(PriorityQueue<Integer> pq, Map<Integer, Integer> checkMap) {
        while (!pq.isEmpty() && checkMap.getOrDefault(pq.peek(), 0) == 0) {
            pq.poll();  // 이미 삭제된 원소는 무시
        }
    }
}

