package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ10809 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] alp = bufferedReader.readLine().split("");

        int[] count = new int[26];
        Arrays.fill(count, -1);

        for (int i = alp.length - 1; i >= 0; i--) {
            int diff = alp[i].charAt(0) - 'a';

            count[diff] = i;
        }

        for (int c : count) {
            System.out.print(c + " ");
        }
    }
}
