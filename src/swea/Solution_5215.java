package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5215 {

    private static class Food {
        int score, cal;

        public Food(int score, int cal) {
            this.score = score;
            this.cal = cal;
        }
    }

    static int foodCount, limitCal;
    static Food[] foods;
    static int maxScore;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for (int t = 0; t < tc; t++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            foodCount = Integer.parseInt(stringTokenizer.nextToken());
            limitCal = Integer.parseInt(stringTokenizer.nextToken());
            foods = new Food[foodCount];
            maxScore = 0;

            for (int i = 0; i < foodCount; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                int score = Integer.parseInt(stringTokenizer.nextToken());
                int cal = Integer.parseInt(stringTokenizer.nextToken());

                foods[i] = new Food(score, cal);
            }

            dfs(0, 0, 0);

            stringBuilder.append("#").append(t + 1).append(" ").append(maxScore).append("\n");

        }
        System.out.println(stringBuilder);

    }

    private static void dfs(int depth, int totalCal, int totalScore) {
        if (totalCal > limitCal) return;
        if (totalScore > maxScore) maxScore = totalScore;
        if (depth >= foodCount) return;

        dfs(depth + 1, totalCal + foods[depth].cal, totalScore + foods[depth].score);
        dfs(depth + 1, totalCal, totalScore);
    }


}
