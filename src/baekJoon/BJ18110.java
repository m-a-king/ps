package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;
import static java.lang.Math.round;

public class BJ18110 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = parseInt(bufferedReader.readLine());
        int[] difficulty = new int[n];

        for (int i = 0; i < n; i++) {
            difficulty[i] = parseInt(bufferedReader.readLine());
        }

        Arrays.sort(difficulty);

        int cutting = (int) round((double) difficulty.length * 0.15);
        int total = 0;
        for (int i = cutting; i < n - cutting; i++) {
            total += difficulty[i];
        }
        if (total == 0) {
            System.out.println("0");
        } else {
            int average = (int) round((double) total / (n - 2 * cutting));

            System.out.println(average);
        }
    }
}
