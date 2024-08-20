package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2467 {

    static int p1, p2, firstPositiveIdx;
    static int[] numbers;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        StringBuilder stringBuilder = new StringBuilder();
        numbers = new int[n];

        numbers[0] = Integer.parseInt(stringTokenizer.nextToken());
        if (numbers[0] > 0) {
            stringBuilder.append(numbers[0]).append(" ").append(stringTokenizer.nextToken());
            System.out.println(stringBuilder);
            return;
        }

        firstPositiveIdx = -1;

        for (int i = 1; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
            if (firstPositiveIdx == -1 && numbers[i] >= 0) {
                firstPositiveIdx = i;
            }
        }

        if (firstPositiveIdx == -1) {
            stringBuilder.append(numbers[n - 2]).append(" ").append(numbers[n - 1]);
            System.out.println(stringBuilder);
            return;
        }

        if (numbers[n - 1] < 0) {
            stringBuilder.append(numbers[n - 2]).append(" ").append(numbers[n - 1]);
            System.out.println(stringBuilder);
            return;
        }

        p1 = 0;
        p2 = n - 1;

        int minSum = Integer.MAX_VALUE;
        int minP1 = -1;
        int minP2 = -1;

        while (p1 < p2) {
            int sum = numbers[p1] + numbers[p2];

            if (sum == 0) {
                stringBuilder.append(numbers[p1]).append(" ").append(numbers[p2]);
                System.out.println(stringBuilder);
                return;
            }

            if (Math.abs(minSum) > Math.abs(sum)) {
                minSum = sum;
                minP1 = p1;
                minP2 = p2;
            }

            if (sum > 0) {
                p2--;
            } else {
                p1++;
            }
        }

        stringBuilder.append(numbers[minP1]).append(" ").append(numbers[minP2]);
        System.out.println(stringBuilder);
    }
}