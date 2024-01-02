package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BJ11399 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = parseInt(bufferedReader.readLine());

        int[] times = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        int total = 0;

        for (int time : times) {

           total += time * n--;
        }

        System.out.println(total);
    }
}