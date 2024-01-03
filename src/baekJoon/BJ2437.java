package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BJ2437 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(bufferedReader.readLine());

        int[] weights = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int sum = 0;

        for (int i = 0; i < N; i++) {

            if (weights[i] > sum+1) {
                break;
            }

            sum += weights[i];
        }

        System.out.println((sum + 1));

    }


}
