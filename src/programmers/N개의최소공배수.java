package programmers;

public class N개의최소공배수 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{2, 6, 8, 14}));

    }

    static class Solution {

        public int solution(int[] arr) {

            long answer = arr[0];

            for(int i = 1; i < arr.length; i++) {
                answer = answer / calcGCD(answer, arr[i]) * arr[i];
            }

            return Math.toIntExact(answer);
        }

        private long calcGCD(long a, long b) {
            while (b != 0) {
                System.out.println("a = " + a);
                System.out.println("b = " + b);
                long temp = a % b;
                System.out.println("temp = " + temp);
                System.out.println();
                a = b;
                b = temp;
            }

            System.out.println("a : return :  = " + a);
            return a;
        }
    }
}
