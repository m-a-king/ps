package programmers;

import java.util.*;

public class 충돌위험찾기 {

    public static void main(String[] args) {
        int[][] points = {{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}};
        int[][] routes = {{2, 3, 4, 5}, {1, 3, 4, 5}};

        int answer = solution(points, routes);
        System.out.println(answer);
    }

    private static class Node {
        int row, col, target, route;

        public Node(int row, int col, int target, int route) {
            this.row = row;
            this.col = col;
            this.target = target;
            this.route = route;
        }

    }

    static Map<String, Integer> posCounts;
    static int routeLength;

    public static int solution(int[][] points, int[][] routes) {
        int answer = 0;
        routeLength = routes[0].length;

        Queue<Node> currQueue = new ArrayDeque<>();
        Queue<Node> nextQueue = new ArrayDeque<>();

        for (int i = 0; i < routes.length; i++) {
            int[] route = routes[i];
            int from = route[0] - 1;

            int currRow = points[from][0];
            int currCol = points[from][1];

            currQueue.offer(new Node(currRow, currCol, 1, i));
        }

        while (true) {
            posCounts = new HashMap<>();

            while (!currQueue.isEmpty()) {
                Node node = currQueue.poll();

                int targetRow = points[routes[node.route][node.target] - 1][0];
                int targetCol = points[routes[node.route][node.target] - 1][1];

                String key = node.row + "," + node.col;
                posCounts.put(key, posCounts.getOrDefault(key, 0) + 1);

                int diffRow = node.row - targetRow;
                int diffCol = node.col - targetCol;

                if (check(diffRow, nextQueue, node, diffCol)) continue;

                if (node.target + 1 < routeLength) {
                    node.target++;

                    int nextTargetRow = points[routes[node.route][node.target] - 1][0];
                    int nextTargetCol = points[routes[node.route][node.target] - 1][1];
                    int nextDiffRow = node.row - nextTargetRow;
                    int nextDiffCol = node.col - nextTargetCol;

                    check(nextDiffRow, nextQueue, node, nextDiffCol);
                }
            }

            for (int count : posCounts.values()) {
                if (count > 1) {
                    answer++;
                }
            }

            if (nextQueue.isEmpty()) break;
            currQueue = nextQueue;
            nextQueue = new ArrayDeque<>();
        }

        return answer;
    }

    private static boolean check(int diffRow, Queue<Node> nextQueue, Node node, int diffCol) {
        if (Math.abs(diffRow) > 0) {
            if (diffRow > 0) {
                nextQueue.offer(new Node(node.row - 1, node.col, node.target, node.route));
            } else {
                nextQueue.offer(new Node(node.row + 1, node.col, node.target, node.route));
            }
            return true;
        }

        if (Math.abs(diffCol) > 0) {
            if (diffCol > 0) {
                nextQueue.offer(new Node(node.row, node.col - 1, node.target, node.route));
            } else {
                nextQueue.offer(new Node(node.row, node.col + 1, node.target, node.route));
            }
            return true;
        }

        return false;
    }
}
