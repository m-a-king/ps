package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2961 {
    private static class Food {
        int sour, bitter;

        public Food(int sour, int bitter) {
            this.sour = sour;
            this.bitter = bitter;
        }
    }

    static Food[] foods;
    static int n;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        foods = new Food[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int sour = Integer.parseInt(stringTokenizer.nextToken());
            int bitter = Integer.parseInt(stringTokenizer.nextToken());
            foods[i] = new Food(sour, bitter);
        }


        int totalCombinations = 1 << n; // 2^n 조합

        for (int i = 1; i < totalCombinations; i++) {
            int totalSour = 1;
            int totalBitter = 0;

            // 비트마스킹을 사용하여 모든 조합 탐색
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    totalSour *= foods[j].sour;
                    totalBitter += foods[j].bitter;
                }
            }
            int diff = Math.abs(totalSour - totalBitter);
            minDiff = Math.min(diff, minDiff);
        }

        System.out.println(minDiff);
    }
}