package programmers;

public class 예상대진표 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(8, 4, 7));
    }

    static class Solution {
        public int solution(int n, int a, int b) {
            int answer = 0;

            while (true) {
                if (a == b) {
                    break;
                }
                a = (a + 1) / 2;
                b = (b + 1) / 2;
                answer++;
            }

            return answer;
        }
    }
}
