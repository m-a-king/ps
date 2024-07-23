package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ7570 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int[] dp = new int[n + 1]; // LIS (1씩 증가)
        int max = 0;

        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(stringTokenizer.nextToken());
            dp[number] = dp[number - 1] + 1;
            max = Math.max(max, dp[number]);
        }

        System.out.println(n-max);


    }
}
