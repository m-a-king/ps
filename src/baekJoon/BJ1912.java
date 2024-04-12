package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1912 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        int[] numbers = new int[n];
        int res = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
            if (numbers[i] >= 0) {
                max += numbers[i];
                res = Math.max(res, max);
            } else {
                if (max + numbers[i] > 0) {
                    max += numbers[i];
                } else {
                    max = 0;
                }
            }
        }

        System.out.println(res == 0 ? lessThanZero(numbers) : res);


    }

    private static int lessThanZero(int[] numbers) {
        Arrays.sort(numbers);
        return numbers[numbers.length - 1];
    }
}
