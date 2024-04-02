package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2309 {

    static int[] heights = new int[9];


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int total = 0;

        for (int i = 0; i < 9; i++) {
            heights[i] = Integer.parseInt(bufferedReader.readLine());
            total += heights[i];
        }

        Arrays.sort(heights);

        int diff = total - 100;

        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (heights[i] + heights[j] == diff) {

                    for (int k = 0; k < 9; k++) {
                        if (k == i || k == j) {
                            continue;
                        }
                        System.out.println(heights[k]);
                    }
                    return;
                }
            }
        }


    }


}
