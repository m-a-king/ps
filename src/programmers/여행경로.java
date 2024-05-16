package programmers;

import java.util.*;

public class 여행경로 {

    static class Solution {

        public static void main(String[] args) {

            String[][] param = {{"ICN", "COO"}, {"ICN", "BOO"}, {"COO", "ICN"}, {"BOO", "DOO"}};
            System.out.println(Arrays.toString(solution(param)));
        }

        static List<String> answerList = new ArrayList<>();
        static HashMap<String, PriorityQueue<String>> pathMap = new HashMap<>();
        static int ticketSize;

        public static String[] solution(String[][] tickets) {
            ticketSize = tickets.length;
            String[] answer = new String[ticketSize + 1];

            for (String[] ticket : tickets) {
                PriorityQueue<String> arrivalPQ = pathMap.getOrDefault(ticket[0], new PriorityQueue<>());
                arrivalPQ.offer(ticket[1]);
                pathMap.put(ticket[0], arrivalPQ);

                // pathMap.putIfAbsent(ticket[0], new PriorityQueue<>());
                // pathMap.get(ticket[0]).offer(ticket[1]);
            }

            for (Map.Entry<String, PriorityQueue<String>> entry : pathMap.entrySet()) {
                System.out.println(entry);
            }

            bfs("ICN");

            int i = 0;
            for (String s : answerList) {
                answer[i++] = s;
            }


            return answer;
        }

        private static void bfs(String start) {
            Queue<String> queue = new ArrayDeque<>();
            queue.offer(start);

            while (!queue.isEmpty()) {
                String departure = queue.poll();
                answerList.add(departure);
                System.out.println(departure);

                if (answerList.size() == ticketSize + 1) {
                    return;
                }

                PriorityQueue<String> arrivalPQ = pathMap.get(departure);
                if (arrivalPQ == null || arrivalPQ.isEmpty()) {
                    continue;
                }

                List<String> temp = new ArrayList<>();
                while (!arrivalPQ.isEmpty()) {
                    String arrival = arrivalPQ.poll();
                    if (answerList.size() == ticketSize) {
                        queue.offer(arrival);
                        break;
                    } else if (pathMap.get(arrival) != null && !pathMap.get(arrival).isEmpty()) {
                        for (String t : temp) {
                            arrivalPQ.offer(t);
                        }
                        queue.offer(arrival);
                        break;
                    } else {
                        temp.add(arrival);
                    }
                }
            }
        }
    }
}

