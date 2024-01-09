package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Math.ceil;

public class BJ2869 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int up = input[0];
        int down = input[1];
        int target = input[2];
        int dayCount = 0;

        int speed = up - down;
        if (up == dayCount) {
            System.out.println("1");
        }

        dayCount += (int) ceil((double) (target - down) / speed);


        System.out.println(dayCount);

    }
}
