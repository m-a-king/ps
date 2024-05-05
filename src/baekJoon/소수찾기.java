package baekJoon;

import java.util.HashSet;
import java.util.Set;

public class 소수찾기 {

    static class Solution {

        public static int[] splitNum;
        static Set<Integer> canNumSet = new HashSet<>();

        public static void main(String[] args) {
            System.out.println(solution("17"));
        }


        public static int solution(String numbers) {
            int answer = 0;
            int intNumber = Integer.parseInt(numbers);

            splitNum = new int[numbers.length()];
            for (int i = 0; i < numbers.length(); i++) {
                splitNum[i] = intNumber % 10;
                intNumber /= 10;
            }

            boolean[] visited = new boolean[splitNum.length];

            for (int i = 1; i <= numbers.length(); i++) {
                createNumber(i, 0, visited);
            }

            for (int num : canNumSet) {
                if (isPrime(num)) {
                    answer++;
                }
            }


            return answer;
        }

        private static boolean isPrime(int num) {
            if (num == 0 || num == 1) {
                return false;
            }
            if (num == 2) {
                return true;
            }
            if (num % 2 == 0) {
                return false;
            }
            for (int i = 3; i <= Math.sqrt(num); i+=2) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        }

        private static void createNumber(int depth, int number, boolean[] visited) {

            if (depth == 0) {
                canNumSet.add(number);
                return;
            }

            for (int i = 0; i < splitNum.length; i++) {
                if (visited[i]) {
                    continue;
                }

                number = number * 10 + splitNum[i];

                visited[i] = true;
                createNumber(depth - 1, number, visited);
                number = number / 10;
                visited[i] = false;
            }


        }
    }


}
