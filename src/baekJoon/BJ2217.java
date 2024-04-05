package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BJ2217 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        int[] ropes = new int[n];

        for (int i = 0; i < n; i++) {
            ropes[i] = Integer.parseInt(bufferedReader.readLine());
        }

        Arrays.sort(ropes);

        int max = 0;
        for (int i = 1; i <= ropes.length; i++) {
            int currentRopes = ropes[ropes.length - i];

            if (currentRopes * i >= max) {
                max = currentRopes * i;
            }
        }

        System.out.println(max);
    }
}
