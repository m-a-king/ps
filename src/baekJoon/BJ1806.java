package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1806 {
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int s = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        numbers = new int[n+1];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int start = 0;
        int end = 0;
        int min = Integer.MAX_VALUE;

        int current = 0;

        while (true) {
            if (end > n) {
                break;
            }

            if (current >= s) {
//                System.out.println("현재 값이 목표값 이상");
//                System.out.println(start);
//                System.out.println(end);
//                System.out.println(current);
//                System.out.println();

                min = Math.min(min, end - start);
                current -= numbers[start];
                start++;
            } else {
//                System.out.println("현재 값이 목표값 이하");
//                System.out.println(start);
//                System.out.println(end);
//                System.out.println(current);
//                System.out.println();

                current += numbers[end];
                end++;
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? 0 : min);

    }

}
