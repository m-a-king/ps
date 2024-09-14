package test;

import java.util.Arrays;

public class test4_p {

    public static int solution(int[][] targets) {
        int answer = 0;

        Arrays.sort(targets, (a, b) -> a[1] - b[1]);

        int shot = 0;

        for (int i = 0; i < targets.length; i++) {
            if (targets[i][0] >= shot) {
                shot = targets[i][1];
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] input = {
                {4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}
        };

        int answer = solution(input);
        System.out.println(answer);


    }
}
