package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ12099 {

    private static class Food {
        int spicy, sweet;

        public Food(int spicy, int sweet) {
            this.spicy = spicy;
            this.sweet = sweet;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        StringBuilder stringBuilder = new StringBuilder();

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int q = Integer.parseInt(stringTokenizer.nextToken());

        Food[] foods = new Food[n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            foods[i] = new Food(a, b);
        }

        Arrays.sort(foods, (a, b) -> a.spicy - b.spicy);

        for (int i = 0; i < q; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int u = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            // u 이상 첫 번째 값, lower bound
            int startIdxBySpicy = lowerBoundSpicy(foods, u);
            // v 이하 첫 번째 값, upper bound
            int endIdxBySpicy = upperBoundSpicy(foods, v);

//            Food[] filteredFoods = Arrays.copyOfRange(foods, startIdxBySpicy, endIdxBySpicy + 1);
//            Arrays.sort(filteredFoods, (a, b) -> a.sweet - b.sweet);
//
//            // x 이상 첫 번째 값, lower bound
//            int startIdxBySweet = lowerBoundSweet(filteredFoods, x);
//            // y 이하 첫 번째 값, upper bound
//            int endIdxBySweet = upperBoundSweet(filteredFoods, y);

            int count = 0;
            for (int j = startIdxBySpicy; j <= endIdxBySpicy; j++) {
                if (x <= foods[j].sweet && foods[j].sweet <= y) {
                    count++;
                }
            }

//            stringBuilder.append(endIdxBySweet - startIdxBySweet + 1).append("\n");
            stringBuilder.append(count).append("\n");
        }

        System.out.println(stringBuilder);
    }

    private static int lowerBoundSpicy(Food[] foods, int target) {
        int start = 0;
        int end = foods.length;

        while (start < end) {
            int mid = (start + end) / 2;
            if (foods[mid].spicy < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }

    private static int upperBoundSpicy(Food[] foods, int target) {
        int start = 0;
        int end = foods.length;

        while (start < end) {
            int mid = (start + end) / 2;
            if (foods[mid].spicy <= target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return end - 1;
    }

    private static int lowerBoundSweet(Food[] foods, int target) {
        int start = 0;
        int end = foods.length;

        while (start < end) {
            int mid = (start + end) / 2;
            if (foods[mid].sweet < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }

    private static int upperBoundSweet(Food[] foods, int target) {
        int start = 0;
        int end = foods.length;

        while (start < end) {
            int mid = (start + end) / 2;
            if (foods[mid].sweet <= target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return end - 1;
    }
}