package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BJ1932 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(bufferedReader.readLine());

        if (N == 1) {
            System.out.println(bufferedReader.readLine());
            return;
        }

        int[] temp = new int[N + 1];
        int[] input = new int[N + 1];
        int[] res = new int[N + 1];

        temp[1] = parseInt(bufferedReader.readLine());

        for (int i = 1; i < N; i++) {
            String[] inputS = bufferedReader.readLine().split(" ");

            for (int j = 1; j <= inputS.length; j++) {
                input[j] = parseInt(inputS[j - 1]);
            }

            for (int j = 1; j <= inputS.length; j++) {
                res[j] = Math.max(input[j] + temp[j], input[j] + temp[j - 1]);
            }

            temp = Arrays.copyOf(res, res.length);
        }

        int max = 0;
        for (int number : res) {
            if (max < number) {
                max = number;
            }
        }



        System.out.println(max);


    }
}
