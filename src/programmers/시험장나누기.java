package programmers;

import java.util.ArrayList;
import java.util.List;

public class 시험장나누기 {

    public static void main(String[] args) {
        int[] num = {12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1};
        int[][] links = {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {8, 5}, {2, 10}, {3, 0}, {6, 1}, {11, -1}, {7, 4}, {-1, -1}, {-1, -1}};
        Solution.solution(3, num, links);
    }
    static class Solution {

        static private class Node {
            int value, right, left;

            public Node(int value, int right, int left) {
                this.value = value;
                this.right = right;
                this.left = left;
            }
        }
        public static int solution(int k, int[] num, int[][] links) {
            int answer = 0;

            List<Node> nodes = new ArrayList<>();
            int maxValue = 0; // answer >= maxValue
            int totalValue = 0;

            for (int i = 0; i < num.length; i++) {
                nodes.add(i, new Node(num[i], links[i][0], links[i][1]));
                totalValue += num[i];
                maxValue = Math.max(maxValue, num[i]);
            }

            int avgGroupValue = totalValue / k; // answer  avgGroupValue

            System.out.println("avgGroupValue = " + avgGroupValue);
            System.out.println("maxValue = " + maxValue);



            return answer;
        }
    }
}
