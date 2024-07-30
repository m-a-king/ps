package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2073 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int D = Integer.parseInt(stringTokenizer.nextToken()); // 강까지의 거리
        int P = Integer.parseInt(stringTokenizer.nextToken()); // 파이프 개수

        int[] dp = new int[D + 1];

        dp[0] = Integer.MAX_VALUE;

//        System.out.println();
        for (int i = 0; i < P; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int L = Integer.parseInt(stringTokenizer.nextToken()); // 길이
            int C = Integer.parseInt(stringTokenizer.nextToken()); // 용량
//            System.out.println("L = " + L);
//            System.out.println("C = " + C);

            for (int j = D; j >= L; j--) {
                dp[j] = Math.max(dp[j], Math.min(C, dp[j - L]));
//                System.out.println("j = " + j + ", dp[j] = " + dp[j] + ", C = " + C + ", dp[j-L] = " + dp[j - L]);
//                System.out.println("dp array: " + Arrays.toString(dp));
            }
//            System.out.println();
        }

        System.out.println(dp[D]);
    }
}