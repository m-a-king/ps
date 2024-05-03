package programmers;

import java.util.Arrays;

public class K번째수 {

    class Solution {


        public static int[] solution(int[] array, int[][] commands) {
            int[] answer = new int[commands.length];


            for (int tc = 0; tc < commands.length; tc++) {
                int start = commands[tc][0] - 1;
                int end = commands[tc][1] - 1;
                int[] tempArray = new int[end - start + 1];
                int idx = 0;
                for (int i = start; i <= end; i++) {
                    tempArray[idx] = array[i];
                    idx++;
                }

                Arrays.sort(tempArray);
                answer[tc] = tempArray[commands[tc][2] - 1];
                System.out.println();
                for (int a : tempArray) {
                    System.out.print(a);
                }
                System.out.println();
                System.out.println("answer" + tc + "= " + answer[tc]);
            }

            return answer;
        }
    }
}

