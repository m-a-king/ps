package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class BJ1149 {


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());

        int[] current = new int[3];

        for (int i = 0; i < n; i++) {
            int[] rgb = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int r = rgb[0];
            int g = rgb[1];
            int b = rgb[2];

            int[] next = new int[3];

            if (current[0] == 0) {
                next[0] = r;
                next[1] = g;
                next[2] = b;
            } else {
                next[0] = r + min(current[1], current[2]);
                next[1] = g + min(current[0], current[2]);
                next[2] = b + min(current[0], current[1]);
            }

            current = next;
        }


        System.out.println(min(current[2], min(current[0], current[1])));

    }
}
