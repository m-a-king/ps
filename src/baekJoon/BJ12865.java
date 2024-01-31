package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ12865 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] NK = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(NK[0]);
        int K = Integer.parseInt(NK[1]);

        int[] weight = new int[N];
        int[] value = new int[N];

        for (int i = 0; i < N; i++) {
            String[] WV = bufferedReader.readLine().split(" ");
            weight[i] = Integer.parseInt(WV[0]);
            value[i] = Integer.parseInt(WV[1]);
        }

        int[][] dp = new int[N + 1][K + 1];

        for (int itemCount = 0; itemCount <= N; itemCount++) {
            for (int weightLimit = 0; weightLimit <= K; weightLimit++) {
                if (itemCount == 0 || weightLimit == 0) {
                    dp[itemCount][weightLimit] = 0;
                } else if (weight[itemCount - 1] <= weightLimit) {
                    dp[itemCount][weightLimit] = Math.max(value[itemCount - 1] + dp[itemCount - 1][weightLimit - weight[itemCount - 1]], dp[itemCount - 1][weightLimit]);
                } else {
                    dp[itemCount][weightLimit] = dp[itemCount - 1][weightLimit];
                }

            }
        }

        System.out.println(dp[N][K]);


    }

}

