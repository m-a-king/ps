package programmers;

public class 시소짝꿍 {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{100, 180, 360, 100, 270}));
    }

    static class Solution {
        public long solution(int[] weights) {
            long answer = 0;
            int[][] leftRightRatio = {{1, 2}, {2, 3}, {3, 4}}; // 1,2 == 2,4

            long[] weightCount = new long[1001]; // 1~1000

            for (int w : weights) {
                weightCount[w]++;
            }

            for (int i = 100; i <= 1000; i++) {
                if (weightCount[i] == 0) {
                    continue;
                }

                // 나 제외 * 나랑 같은 몸무게, 나누기 2(페어)
                answer += (weightCount[i] - 1) * weightCount[i] / 2;

                for (int[] ratio : leftRightRatio) {
                    if (i % ratio[0] != 0) { // 나는 왼쪽
                        continue;
                    }

                    int calced = i / ratio[0] * ratio[1];

                    if(calced > 1000 || weightCount[calced] == 0) {
                        continue;
                    }

                    answer += weightCount[i] * weightCount[calced];
                    System.out.println(i);
                    System.out.println(calced);
                }

            }

            return answer;
        }
    }
}
