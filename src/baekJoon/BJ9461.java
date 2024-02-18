package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ9461 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        int[] ns = new int[t];
        int max = 0;

        for (int i = 0; i < t; i++) {
            ns[i] = Integer.parseInt(bufferedReader.readLine());
            max = Math.max(max, ns[i]);
        }

        long[] spiral = new long[max+1];

        spiral[1] = 1;
        spiral[2] = 1;
        spiral[3] = 1;
        spiral[4] = 2;
        spiral[5] = 2;
        spiral[6] = 3;
        spiral[7] = 4;
        spiral[8] = 5;
        spiral[9] = 7;
        spiral[10] = 9;

        for (int i = 11; i <= max; i++) {
            spiral[i] = spiral[i - 2] + spiral[i - 3];
        }

        for (int n : ns) {
            System.out.println(spiral[n]);
        }

    }
}
