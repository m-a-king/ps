package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2138 {

    static int n;
    static boolean[] onStart, offStart, target;
    static int minAns = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());

        onStart = new boolean[n];
        offStart = new boolean[n];
        target = new boolean[n];

        String inputStart = bufferedReader.readLine();
        String inputTarget = bufferedReader.readLine();
        for (int i = 0; i < n; i++) {
            onStart[i] = inputStart.charAt(i) == '1';
            target[i] = inputTarget.charAt(i) == '1';
        }

        offStart = Arrays.copyOf(onStart, n);

        // 첫 번째 전구를 키지 않는 경우
        dfs(1, 0, offStart);

        // 첫 번째 전구를 키는 경우
        dfs(1, 1, onSwitch(onStart, 0));

        System.out.println(minAns == Integer.MAX_VALUE ? -1 : minAns);


    }

    private static void dfs(int depth, int count, boolean[] curr) {

        if (depth == n) {
            if (curr[depth - 1] != target[depth - 1]) return;

            minAns = Math.min(minAns, count);
            return;
        }

        if (curr[depth - 1] != target[depth - 1])
            dfs(depth + 1, count + 1, onSwitch(curr, depth));
        else
            dfs(depth + 1, count, curr);
    }

    private static boolean[] onSwitch(boolean[] curr, int depth) {
        for (int i = depth - 1; i <= depth + 1; i++) {
            if (0 <= i && i < n) curr[i] = !curr[i];
        }

        return curr;
    }
}
