package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2031 {

    static int t, n, d, k;
    static int[] time;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        t = Integer.parseInt(stringTokenizer.nextToken());
        n = Integer.parseInt(stringTokenizer.nextToken());
        d = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());

        time = new int[n];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        for (int i = 0; i < n; i++) {
            time[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        Arrays.sort(time);

        int[] teaCnt = new int[n];
        for (int i = 0; i < n; i++) {
            int start = i;
            int end = n - 1;
            int teaTime = time[i];
            while (start <= end) {
                int mid = (start + end) / 2;
                if (time[mid] >= teaTime + d) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            teaCnt[i] = start - i;
        }

        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i + teaCnt[i]][j] = Math.max(dp[i + teaCnt[i]][j], dp[i][j - 1] + teaCnt[i]);
                dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);
            }
        }

        System.out.println(Arrays.stream(dp[n]).max().getAsInt());
    }
}