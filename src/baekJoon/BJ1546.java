package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BJ1546 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());

        int[] scores = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        double total = 0;

        int max = 0;
        for (int score : scores) {
            if (score > max) {
                max = score;
            }

            total += score;
        }



        System.out.println(((total / max) * 100 / n));
    }
}
