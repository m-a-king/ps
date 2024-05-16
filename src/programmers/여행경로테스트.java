package programmers;

import java.util.*;

public class 여행경로테스트 {

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

            for (String[] ticket : tickets) {
                PriorityQueue<String> arrivalPQ = pathMap.getOrDefault(ticket[0], new PriorityQueue<>());
                arrivalPQ.offer(ticket[1]);
                pathMap.put(ticket[0], arrivalPQ);
            }

            bfs("ICN");

            return answerList.toArray(new String[0]);
        }

        private static void bfs(String start) {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(new Node(start, new ArrayList<>(), pathMap));

            while (!queue.isEmpty()) {
                Node node = queue.poll();
                String departure = node.city;
                List<String> currentRoute = new ArrayList<>(node.route);
                currentRoute.add(departure);

                if (currentRoute.size() == ticketSize + 1) {
                    answerList = currentRoute;
                    return;
                }

                PriorityQueue<String> arrivals = new PriorityQueue<>(node.pathMap.getOrDefault(departure, new PriorityQueue<>()));
                if (arrivals == null || arrivals.isEmpty()) {
                    continue;
                }

                while (!arrivals.isEmpty()) {
                    String arrival = arrivals.poll();
                    List<String> nextRoute = new ArrayList<>(currentRoute);

                    HashMap<String, PriorityQueue<String>> nextPathMap = new HashMap<>();
                    for (Map.Entry<String, PriorityQueue<String>> entry : node.pathMap.entrySet()) {
                        nextPathMap.put(entry.getKey(), new PriorityQueue<>(entry.getValue()));
                    }
                    nextPathMap.get(departure).remove(arrival);

                    queue.offer(new Node(arrival, nextRoute, nextPathMap));
                }
            }
        }

        static class Node {
            String city;
            List<String> route;
            HashMap<String, PriorityQueue<String>> pathMap;

            Node(String city, List<String> route, HashMap<String, PriorityQueue<String>> pathMap) {
                this.city = city;
                this.route = route;
                this.pathMap = pathMap;
            }
        }
    }
}
