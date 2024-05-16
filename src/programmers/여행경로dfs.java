package programmers;

import java.util.*;

public class 여행경로dfs {

    static class Solution {

        public static void main(String[] args) {
            System.out.println(Arrays.toString(solution(new String[][]{{"ICN", "D"}, {"D", "ICN"}, {"ICN", "B"}})));
        }

        static HashMap<String, PriorityQueue<String>> pathMap = new HashMap<>();
        static LinkedList<String> route = new LinkedList<>();

        public static String[] solution(String[][] tickets) {
            String[] answer = new String[tickets.length + 1];

            for (String[] ticket : tickets) {
                pathMap.putIfAbsent(ticket[0], new PriorityQueue<>());
                pathMap.get(ticket[0]).offer(ticket[1]);
            }

            dfs("ICN");

            for (int i = 0; i < answer.length; i++) {
                answer[i] = route.get(i);
            }

            return answer;
        }

        private static void dfs(String departure) {
            PriorityQueue<String> arrivals = pathMap.get(departure);

            while (arrivals != null && !arrivals.isEmpty()) {
                dfs(arrivals.poll());
            }

            route.addFirst(departure);
        }
    }
}
