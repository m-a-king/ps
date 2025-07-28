package programmers;

public class 다음큰숫자 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(15));

    }

    static class Solution {
        public int solution(int n) {
            int count = 0;
            for (int i = 0; i < 20; i++) {
                if (((n >> i) & 1) == 1) {
                    n &= ~(1 << i);
                    count++;
                    continue;
                }

                if (count > 0) {
                    n |= 1 << i;
                    break;
                }
            }

            final int left = (1 << (count - 1)) - 1;

            return n + left;
        }
    }
}
