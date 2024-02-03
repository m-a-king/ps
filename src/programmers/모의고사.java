package programmers;

import java.util.ArrayList;
import java.util.List;

public class 모의고사 {

    public int[] solution(int[] answers) {
        List<Integer> answer = new ArrayList<>();
        int[] collect = new int[3];

        int[][] gum = new int[3][5];
        gum[0] = new int[]{1, 2, 3, 4, 5};
        gum[1] = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
        gum[2] = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        for (int i = 0; i < answers.length; i++) {
            int i0 = i % 5;
            int i1 = i % 8;
            int i2 = i % 10;
            if (answers[i] == gum[0][i0]) {
                collect[0]++;
            }if (answers[i] == gum[1][i1]) {
                collect[1]++;
            }if (answers[i] == gum[2][i2]) {
                collect[2]++;
            }
        }

        int max = Math.max(Math.max(collect[0], collect[1]), collect[2]);

        for (int i = 0; i < 3; i++) {

            if (collect[i] == max) {
                answer.add(i + 1);
            }
        }

        int[] result = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }
}
