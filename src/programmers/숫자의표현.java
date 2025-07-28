package programmers;

public class 숫자의표현 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(2));
    }

    static class Solution {
        public int solution(int n) {
            int answer = 0;

            int acc = 0;
            int left = 1;
            int right = 1;

            if(n == 1) return 1;
            if(n == 2) return 1;

            for (int i = 1; i <= n / 2 + 1; i++) {

                acc += i;
                if (acc == n) {
                    answer++;
                    continue;
                }

                while (acc > n) {
                    acc -= left++;
                }

                if (acc == n) {
                    answer++;
                }
            }

            return answer + 1;
        }
    }
}
