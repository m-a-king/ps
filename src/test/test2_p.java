package test;

import java.util.ArrayList;
import java.util.Arrays;

public class test2_p {

    public static int[] solution(int[][] edges) {
        int[] answer = new int[4];

        int[] in = new int[1_000_001];
        int[] out = new int[1_000_001];

        int nodeCount = 0;

        for (int[] edge : edges) {
            in[edge[1]]++;
            out[edge[0]]++;

            nodeCount = Math.max(nodeCount, Math.max(edge[0], edge[1]));
        }

        for (int i = 1; i <= nodeCount; i++) {
            if (in[i] == 0 && out[i] >= 2) {
                answer[0] = i;
            } else if (in[i] > 0 && out[i] == 0) {
                answer[2]++;
            } else if (in[i] >= 2 && out[i] == 2) {
                answer[3]++;
            }
        }
        answer[1] = out[answer[0]] - answer[2] - answer[3];

        return answer;
    }


    public static void main(String[] args) {


        int[][] input = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
        int[] answer = solution(input);

        System.out.println(Arrays.toString(answer));
    }
}
