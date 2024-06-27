package programmers;

import java.util.HashMap;
import java.util.Map;

public class devTest1 {

    public static void main(String[] args) {
        int[] answer = Solution.solution(new int[][]{{1, 3, 2, 0}, {2, 0, 1, 3}, {1, 2, 0, 3}});

        for (int a : answer) {
            System.out.println(a);
        }
    }

    static class Solution {
        public static int[] solution(int[][] arr) {
            int rowSize = arr.length;
            int colSize = arr[0].length;

            int[] answer = new int[colSize];

            Map<Integer, Integer> tMap = new HashMap<>();

            for (int i = 0; i < colSize; i++) {
                tMap.put(arr[0][i], i); // value : idx
            }

            for (int i = 1; i < rowSize; i++) {
                Map<Integer, Integer> cMap = new HashMap<>();

                for (int j = 0; j < colSize; j++) {
                    cMap.put(arr[i][j], j);
                }

                for (int value : cMap.keySet()) {
                    int tIdx = tMap.get(value);
                    int cIdx = cMap.get(value);

                    answer[value] += Math.abs(tIdx - cIdx);
                }

                tMap = cMap;
            }

            return answer;
        }
    }
}
