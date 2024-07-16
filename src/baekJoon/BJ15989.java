package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ15989 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        int[][] counts = new int[10001][4];
        counts[1][1] = 1; // 1을 만들고 마지막이 1 -> 1+1

        counts[2][1] = 1; // 1+1
        counts[2][2] = 1; // 2

        counts[3][1] = 1; // 1+1+1, 2+1(오름차순이 아니라서 카운트 x)
        counts[3][2] = 1; // 1+2
        counts[3][3] = 1; // 3
        // 여기까지만 초기화해도 됨

        counts[4][1] = 1; // 1+1+1+1                    -> counts[3][1]+1,
        counts[4][2] = 2; // 1+1+2, 2+2                 -> counts[2][1]+2, counts[2][2]+2
        counts[4][3] = 1; // 1+3                        -> counts[1][1]+3
        // counts[4][4] = x; // 4부터 불가능

        counts[5][1] = 1; // 1+1+1+1+1                  -> counts[4][1]+1
        counts[5][2] = 2; // 1+1+1+2, 1+2+2             -> counts[3][1] + 2, counts[3][2] + 2
        counts[5][3] = 2; // 1+1+3, 2+3                 -> counts[2][1] + 3, counts[2][2]+3

        counts[6][1] = 1; // 1+1+1+1+1+1                -> counts[5][1] + 1
        counts[6][2] = 3; // 1+1+1+1+2, 1+1+2+2, 2+2+2  -> counts[4][1] + 2, counts[4][2] + 2
        counts[6][3] = 3; // 1+1+1+3, 1+2+3, 3+3        -> counts[3][1] + 3, counts[3][2] + 3, counts[3][3] + 3

        StringBuilder stringBuilder = new StringBuilder();

        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(bufferedReader.readLine());
            if (counts[n][1] == 0) {
                for (int i = 6; i <= n; i++) {
                    counts[i][1] = counts[i-1][1];
                    counts[i][2] = counts[i-2][1] + counts[i-2][2];
                    counts[i][3] = counts[i-3][1] + counts[i-3][2] + counts[i-3][3];
                }
            }
            stringBuilder.append(counts[n][1] + counts[n][2] + counts[n][3]).append("\n");
        }

        System.out.println(stringBuilder);
    }
}
