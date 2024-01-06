package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BJ1920 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());

        int[] input1 = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        int m = parseInt(bufferedReader.readLine());

        int[] input2 = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < m; i++) {
            findNumber(input1, input2[i]);
        }

    }

    private static void findNumber(int[] number, int target) {

        long start = 0;
        long end = number.length - 1;
        while (start <= end) {
            long mid = (start + end) / 2;
            if (target > number[(int) mid]) {
                start = mid + 1;
            } else if (target < number[(int) mid]) {
                end = mid -1 ;
            } else {
                System.out.println("1");
                return;
            }
        }
        System.out.println("0");
    }
}
