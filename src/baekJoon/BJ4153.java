package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Math.pow;

public class BJ4153 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            int[] input = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

            if (input[0] == 0 && input[1] == 0 && input[2] == 0) {
                break;
            }

            int a = (int) pow(input[0], 2);
            int b = (int) pow(input[1], 2);
            int c = (int) pow(input[2], 2);

            if (a + b == c) {
                System.out.println("right");

            } else {
                System.out.println("wrong");
            }
        }


    }
}
