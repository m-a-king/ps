package programmers;

public class 두원사이의정수쌍 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(2, 3));

    }

    static class Solution {

        public long solution(int r1, int r2) {
            long answer = 0;

            long accR2 = 0;
            // x^2 + y^2 = r^2
            for (int x = 1; x <= r2; x++) {
                final double canReach = Math.sqrt((long) r2 * r2 - (long) x * x);

                accR2 += (long) canReach + 1;
                System.out.println(canReach);
            }

            long accR1 = 0;
            for (int x = 1; x <= r1; x++) {
                final double canReach = Math.sqrt((long) r1 * r1 - (long) x * x);

                if (canReach % 1 == 0) {
                    accR1 += (long) canReach;
                } else {
                    accR1 += (long) canReach + 1;
                }

            }

            System.out.println("accR1 = " + accR1);
            System.out.println("accR2 = " + accR2);

            return (accR2 * 4) - (accR1 * 4);
        }
    }

}
