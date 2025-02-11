package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ32202 {

    static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        long[][] students = new long[N + 1][2];

        students[1][0] = 2;
        students[1][1] = 1;

        for (int i = 2; i <= N; i++) {
            students[i][0] = (((students[i - 1][0] + students[i - 1][1]) % MOD) * 2) % MOD;
            students[i][1] = students[i - 1][0];
        }

        System.out.println((students[N][0] + students[N][1]) % MOD);
    }
}
