package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1182 {
    static int s = 0;
    static int n = 0;
    static int count = 0;
    static int[] number;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        s = Integer.parseInt(stringTokenizer.nextToken());

        number = new int[n];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        for (int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        dfs(0,0);

        // s가 0인 경우, 아무것도 선택하지 않는 경우를 제외
        if (s == 0) {
            count--;
        }

        System.out.println(count);
    }

    private static void dfs(int depth, int sum) {

        if (depth == n) {
            if (sum == s) {
                count++;
            }
            return;
        }

        dfs(depth + 1, sum + number[depth]);
        dfs(depth + 1, sum);

    }
}
