package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BJ3687 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        String[] dp = new String[101];
        dp[2] = "1";
        dp[3] = "7";
        dp[4] = "4";
        dp[5] = "2";
        dp[6] = "6";
        dp[7] = "8";
        dp[8] = "10";

        for (int i = 9; i <= 100; i++) {
            if (i % 7 == 0) {
                dp[i] = dp[i - 7] + "8";
                continue;
            }
            if (i % 7 == 2) {
                dp[i] = dp[i - 7] + "8";
                continue;
            }
            PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
                if (a.length() - b.length() == 0) {
                    return a.compareTo(b);
                }
                return a.length() - b.length();
            });
            pq.offer(dp[i - 2] + "1");
            pq.offer(dp[i - 3] + "7");
            pq.offer(dp[i - 4] + "4");
            pq.offer(dp[i - 5] + "2");
            pq.offer(dp[i - 6] + "0");
            pq.offer(dp[i - 7] + "8");

            dp[i] = pq.poll();

        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int target = Integer.parseInt(bufferedReader.readLine());
            String max = getMax(target);
            String min = dp[target];
            stringBuilder.append(min).append(" ").append(max).append("\n");
        }

        System.out.println(stringBuilder);

//        for (int i = 9; i <= 100; i++) {
//
//            System.out.println(i + " : " + dp[i]);
//
//        }

    }

    private static String getMax(int target) {
        StringBuilder res = new StringBuilder();
        res.append(target % 2 == 0 ? "1" : "7");
        for (int i = 2; i < target-1; i += 2) {
            res.append("1");
        }
        return res.toString();
    }
}
