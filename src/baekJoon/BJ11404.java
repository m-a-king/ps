package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11404 {

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int m = Integer.parseInt(bufferedReader.readLine());

        int[][] res = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(res[i], INF); // 예시로 큰 수 사용
            res[i][i] = 0; // 자기 자신으로의 경로 비용은 0
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            res[a][b] = Math.min(res[a][b], c); // 더 작은 비용으로 경로 갱신
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (k != i && k != j && i != j) {
                        res[i][j] = Math.min(res[i][j], res[i][k] + res[k][j]);
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    res[i][j] = Math.min(res[i][j], res[i][k] + res[k][j]);
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (res[i][j] == INF) {
                    stringBuilder.append(0).append(" ");
                    continue;
                }
                stringBuilder.append(res[i][j]).append(" ");
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder);
    }


}
