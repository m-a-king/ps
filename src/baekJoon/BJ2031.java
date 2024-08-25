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

        int[] coverages = bs();
        System.out.println(Arrays.toString(coverages));

        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                // 현재 위치 i에서 coverages[i]만큼의 범위를 선택했을 때, j번째 선택에서 얻을 수 있는 최대 점수를 갱신
                // dp[i][j-1]는 i 에서 j-1개의 선택으로 얻을 수 있는 최대 점수
                // 여기에 coverages[i] (현재 위치 i에서 덮을 수 있는 최대 범위의 값)를 더해서 j번째 선택에 대한 점수를 갱신
                // 즉, 이게 선택에 대한 조절
                dp[i + coverages[i]][j] = Math.max(dp[i + coverages[i]][j], dp[i][j - 1] + coverages[i]);

                // 현재 위치 i에서 j개의 선택을 유지하면서 다음 위치로 이동했을 때, j개의 선택으로 얻을 수 있는 최대 점수를 갱신
                // 즉, 항상 더 좋은 값을 미뤄주기
                dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);

                // DP 배열 출력
                System.out.println("DP 상태 (탐색범위=" + i + ", 선택개수=" + j + "):");
                printDp(dp);
                System.out.println(); // 줄바꿈
            }
        }

        System.out.println("최종 결과: " + dp[n][k]);
    }

    // lower bound (찾으려는 값보다 1큰 값의 아래 인덱스를 찾기)
    private static int[] bs() {
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            int start = i;
            int end = n;
            int target = time[i] + d;

            while (start < end) {
                int mid = (start + end) / 2;

                if (time[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }

            cnt[i] = start - i;
        }
        return cnt;
    }

    // DP 배열 출력
    private static void printDp(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
    }
}