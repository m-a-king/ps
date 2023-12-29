package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BJ2805 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] split = bufferedReader.readLine().split(" ");
        int count = parseInt(split[0]);
        int need = parseInt(split[1]);

        int[] trees = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int min = 0;
        int max = 0;
        int best = 0;

        for (int i = 0; i < count; i++) {
            if (max < trees[i]) {
                max = trees[i];
            }
        }

        while (true) {

            if (min > max) {
                break;
            }

            long mid = (min + max) / 2;
            long res = 0;

            for (int tree : trees) {
                if ((tree - mid) > 0) {
                    res += tree - mid;
                }
            }

            if (res >= need) {
                min = (int) (mid + 1);
                best = (int) mid;

            } else if (res < need) {
                max = (int) (mid - 1);
            }



        }

        System.out.println(best);
    }
}
