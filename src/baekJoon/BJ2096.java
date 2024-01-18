package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class BJ2096 {

    static int[] maxArrM;
    static int[] minArrM;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());

        int[] first = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        maxArrM = Arrays.copyOf(first, first.length);
        minArrM = Arrays.copyOf(first, first.length);

        for (int i = 1; i < n; i++) {
            int[] input = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            calcSum(maxArrM, minArrM, input);
        }

        System.out.println((Arrays.stream(maxArrM).max().getAsInt() + " " + Arrays.stream(minArrM).min().getAsInt()));

    }

    private static void calcSum(int[] maxArr, int[] minArr, int[] input) {

        int[] maxTemp = new int[3];
        int[] minTemp = new int[3];

        maxTemp[0] = max(maxArr[0] + input[0], maxArr[1] + input[0]);
        minTemp[0] = min(minArr[0] + input[0], minArr[1] + input[0]);

        maxTemp[1] = max(max(maxArr[0] + input[1], maxArr[1] + input[1]), maxArr[2] + input[1]);
        minTemp[1] = min(min(minArr[0] + input[1], minArr[1] + input[1]), minArr[2] + input[1]);

        maxTemp[2] = max(maxArr[1] + input[2], maxArr[2] + input[2]);
        minTemp[2] = min(minArr[1] + input[2], minArr[2] + input[2]);

        maxArrM = maxTemp;
        minArrM = minTemp;

    }
}
