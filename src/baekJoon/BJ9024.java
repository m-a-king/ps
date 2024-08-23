package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ9024 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());

            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int[] numbers = new int[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            Arrays.sort(numbers);
            int p1 = 0;
            int p2 = n - 1;
            int minDiff = Integer.MAX_VALUE;
            int count = 0;

            while (p1 < p2) {
                int sum = numbers[p1] + numbers[p2];
                int diff = k - sum;
                int absDiff = Math.abs(diff);
                if (minDiff == absDiff) {
                    count++;
                }
                if (minDiff > absDiff) {
                    minDiff = absDiff;
                    count = 1;
                }

                if (diff > 0) {
                    p1++;
                } else {
                    p2--;
                }
            }
            stringBuilder.append(count).append("\n");
        }

        System.out.println(stringBuilder);


    }
}
