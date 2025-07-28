package programmers;

import java.util.ArrayDeque;
import java.util.Deque;

public class 점프와순간이동 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(5000));
    }

    static class Solution {
        public int solution(int n) {
            int ans = 0;

            while (n > 1) {
                if (n % 2 == 0) {
                    n /= 2;
                } else {
                    n -= 1;
                    ans++;
                }
            }

            return ans + 1;
        }
    }
}
