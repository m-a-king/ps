package programmers;

import java.util.*;

public class 여행경로dfs {

    static class Solution {

        public static void main(String[] args) {
            String[][] param = {{"ICN", "COO"}, {"ICN", "BOO"}, {"COO", "ICN"}, {"BOO", "DOO"}};

            System.out.println(Arrays.toString(solution(param)));
        }

        static HashMap<String, PriorityQueue<String>> pathMap = new HashMap<>();
        static LinkedList<String> route = new LinkedList<>();

        public static String[] solution(String[][] tickets) {
            String[] answer = new String[tickets.length + 1];

            for (String[] ticket : tickets) {
                pathMap.putIfAbsent(ticket[0], new PriorityQueue<>());
                pathMap.get(ticket[0]).offer(ticket[1]);
            }

            int asd=0;
            dfs("ICN",asd);

            for (int i = 0; i < answer.length; i++) {
                answer[i] = route.get(i);
            }

            return answer;
        }

        private static void dfs(String departure,int i) {
            System.out.println("dfs 호출" + i+"번째");
            PriorityQueue<String> arrivals = pathMap.get(departure);
            System.out.println("departure = " + departure);
            System.out.println("arrivals = " + arrivals);
            System.out.println();

            while (arrivals != null && !arrivals.isEmpty()) {
                String poll = arrivals.poll();
                dfs(poll, i+1);
                System.out.println("poll = " + poll);
                System.out.println();
            }

            route.addFirst(departure);
            System.out.println("추가한다 "+ i+"번째 dfs임 " + departure);
            System.out.println();
        }
    }
}
