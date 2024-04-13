package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1010 {

    static int[][] dp = new int[30][30];

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();

        for (int t = 0; t < tc; t++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int leftSite = Integer.parseInt(stringTokenizer.nextToken());
            int rightSite = Integer.parseInt(stringTokenizer.nextToken());

            stringBuilder.append(combi(rightSite, leftSite)).append("\n");
        }

        System.out.println(stringBuilder);
    }

    private static int combi(int n, int r) {
        if (dp[n][r] > 0) {
            return dp[n][r];
        }

        if (n == r || r == 0) {
            return dp[n][r] = 1;
        }

        return dp[n][r] = combi(n - 1, r - 1) + combi(n - 1, r);
    }
}
