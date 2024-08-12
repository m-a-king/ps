package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ28464 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        int[] potatoes = new int[n];

        for (int i = 0; i < n; i++) {
            potatoes[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        Arrays.sort(potatoes);

        int min = 0;
        int max = 0;

        int arrLength = potatoes.length - 1;

        for (int i = 0; i < n/2; i++) {
            max += potatoes[arrLength - i];
            min += potatoes[i];
        }

        if (n % 2 == 1) {
            max += potatoes[n / 2];
        }

        System.out.println(min + " " + max);
    }
}
