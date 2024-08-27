package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ9252 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        char[] s1 = bufferedReader.readLine().toCharArray();
        char[] s2 = bufferedReader.readLine().toCharArray();

        int s1Length = s1.length;
        int s2Length = s2.length;
        int[][] dp = new int[s1Length + 1][s2Length + 1];
        StringBuilder stringBuilder = new StringBuilder();


        for (int i = 1; i <= s1Length; i++) {
            for (int j = 1; j <= s2Length; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

//        for (int i = 0; i <= s1Length; i++) {
//            for (int j = 0; j <= s1Length; j++) {
//                System.out.print(dp[i][j]);
//            }
//            System.out.println();
//        }

        Stack<Character> stack = new Stack<>();


        int p1 = s1Length - 1;
        int p2 = s2Length - 1;
        while (p1 >= 0 && p2 >= 0) {

            if (s1[p1] == s2[p2]) {

                stack.push(s1[p1]);

                p1--;
                p2--;
            } else {

                if (dp[p1+1][p2+1] == dp[p1][p2+1]) {
                    p1--;
                } else {
                    p2--;
                }

            }
        }

        System.out.println(dp[s1Length][s2Length]);
        if(dp[s1Length][s2Length] == 0) return;

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        System.out.println(result.toString());

    }
}
