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

        select(0, 1, 0, 0);

        System.out.println(minDiff);

    }

    private static void select(int depth, int totalSour, int totalBitter, int selectCnt) {
        if (depth == n) {
            if (selectCnt > 0) {
                int diff = Math.abs(totalSour - totalBitter);
                minDiff = Math.min(diff, minDiff);
            }
            return;
        }


        select(depth + 1, totalSour * foods[depth].sour, totalBitter + foods[depth].bitter, selectCnt+1);
        select(depth + 1, totalSour, totalBitter,selectCnt);

    }
}
