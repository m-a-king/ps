package programmers;

public class 카펫 {

    class Solution {
        public int[] solution(int brown, int yellow) {
            int[] answer = {};

            int yellowHeight = 0;

            while (true) {
                yellowHeight++;
                if (yellow % yellowHeight != 0) {
                    continue;
                }
                int yellowPerFloor = yellow / yellowHeight;
                int needBrown = 4; // 일단 4각 모서리 더함
                needBrown += yellowHeight * 2;
                needBrown += yellowPerFloor * 2;

                if (needBrown == brown) {
                    if (brown + yellow == (yellowPerFloor + 2) * (yellowHeight + 2))
                        answer = new int[]{yellowPerFloor + 2, yellowHeight + 2};
                    return answer;
                }

            }
        }
    }
}
