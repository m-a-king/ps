package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ15650 {
    static int max, count;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        max = parseInt(input[0]);
        count = parseInt(input[1]);

        generateCombination(1, "", 0);
    }

    private static void generateCombination(int start, String output, int depth) {
        if (depth == count) {
            System.out.println(output.trim());
            return;
        }

        for (int i = start; i <= max; i++) {
            generateCombination(i + 1, output + i + " ", depth + 1);
        }
    }
}
