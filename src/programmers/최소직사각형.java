package programmers;

public class 최소직사각형 {

    class Solution {
        public int solution(int[][] sizes) {
            int answer = 0;

            int N = sizes.length;
            int x = 0;
            int y = 0;

            for (int i = 0; i < N; i++) {
                if (sizes[i][0] < sizes[i][1]) {
                    int temp = sizes[i][0];
                    sizes[i][0] = sizes[i][1];
                    sizes[i][1] = temp;
                }

                x = Math.max(x, sizes[i][0]);
                y = Math.max(y, sizes[i][1]);
            }

            answer = x*y;
            return answer;
        }
    }
}
