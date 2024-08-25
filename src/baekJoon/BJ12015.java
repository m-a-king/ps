package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ12015 {

    static int[] numbers;
    static int p = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        numbers = new int[n];

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        numbers[p] = Integer.parseInt(stringTokenizer.nextToken());

        for (int i = 1; i < n; i++) {
            int curr = Integer.parseInt(stringTokenizer.nextToken());
            if (numbers[p] < curr) {
                numbers[++p] = curr;
            } else {
                numbers[bs(curr)] = curr;
            }
        }

//        System.out.println(Arrays.toString(numbers));

        System.out.println(p + 1);
    }

    private static int bs(int target) {
        int left = 0;
        int right = p + 1;


        while (left < right) {
            int mid = (left + right) / 2;

            if (numbers[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
