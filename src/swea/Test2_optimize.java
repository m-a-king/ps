package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.parseInt;
import static java.lang.Math.min;

public class Test2_optimize {

    static int[][] agent;
    static int[] sumCache;
    static int[] maxCache;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = parseInt(bufferedReader.readLine());

        for (int tc = 0; tc < testCase; tc++) {

            int n = parseInt(bufferedReader.readLine());

            agent = new int[n][3];
            sumCache = new int[n];
            maxCache = new int[n];

            for (int i = 0; i < n; i++) {
                agent[i] = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                sumCache[i] = Arrays.stream(agent[i]).sum();
                maxCache[i] = Arrays.stream(agent[i]).max().getAsInt();
            }

            if (n < 3) {
                System.out.println("#" + (tc+1) + " " + -1);
                continue;
            }

            int result = galaxy(n);

            System.out.println("#" + (tc+1) + " " + result);
        }
    }

    private static int galaxy(int n) {

        int minTotalWaste = MAX_VALUE;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int waste = calcSelectAgent(i, j, k);
                    int subWaste = calcUnselectedAgent(i, j, k, n);

                    minTotalWaste = min(minTotalWaste, (waste + subWaste));
                }
            }
        }

        return minTotalWaste;

    }

    private static int calcUnselectedAgent(int a, int b, int c, int n) {

        int total = 0;
        for (int i = 0; i < n; i++) {
            if (i == a || i == b || i == c) {
                continue;
            }
            total += sumCache[i] - maxCache[i];
        }
        return total;
    }

    private static int calcSelectAgent(int a, int b, int c) {

        int minWaste_sA = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (i == j || j == k || i == k) {
                        continue;
                    }
                    int waste = 0;
                    waste += sumCache[a] - agent[a][i];
                    waste += sumCache[b] - agent[b][j];
                    waste += sumCache[c] - agent[c][k];

                    minWaste_sA = min(minWaste_sA, waste);

                }
            }
        }

        return minWaste_sA;

    }
}
