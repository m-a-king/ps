package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Math.min;

public class BJ30960 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        long[] students = Arrays.stream(reader.readLine().split(" ")).mapToLong(Long::parseLong).sorted().toArray();

        int x = 0;
        int y = Integer.MAX_VALUE;

        for (int i = 1; i < N; i += 2) {
            x += students[i] - students[i - 1];

            if (i + 1 < N) {
                y = (int) (min(x, y) + students[i + 1] - students[i]);
            }
        }

        System.out.println(y);
    }
}
