package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static java.util.Collections.min;

public class SW18662 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = parseInt(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            int[] number = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            double min = Double.MAX_VALUE;

            double x = number[1] - number[0];
            double y = number[2] - number[1];
            double z = (double) (number[0] + number[2]) / 2;

            //첫 번째 숫자 바꾸기
            double change1 = number[1] - y;
            double gap1 = abs(number[0] - change1);

            //두 번쨰 숫자 바꾸기
            double change2 = z;
            double gap2 = abs(number[1] - change2);

            //세 번째 숫자 바꾸기
            double change3 = number[1] + x;
            double gap3 = abs(number[2] - change3);

            min = Math.min(Math.min(gap1, gap2), gap3);

            if (min == 0) {
                System.out.println("#" + (i + 1) + " " + 0);

            } else {

                System.out.println("#" + (i + 1) + " " + String.format("%.1f", min));

            }
        }
    }
}
