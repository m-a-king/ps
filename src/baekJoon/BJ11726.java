package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ11726 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(bufferedReader.readLine());

        if (N == 1) {
            System.out.println(1);
            return;
        }

        long[] dp = new long[N+1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2])%10007;
        }

        System.out.println(dp[N]%10007);
    }
}
