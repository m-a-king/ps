package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ3028 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        char[] seq = bufferedReader.readLine().toCharArray();

        int[] pos = {1, 0, 0};
        for (char s : seq) {
            if (s == 'A') {
                int temp = pos[0];
                pos[0] = pos[1];
                pos[1] = temp;
                continue;
            }

            if (s == 'B') {
                int temp = pos[1];
                pos[1] = pos[2];
                pos[2] = temp;
                continue;
            }

            int temp = pos[0];
            pos[0] = pos[2];
            pos[2] = temp;

        }

        for (int i = 0; i < 3; i++) {
            if (pos[i] == 1) {
                System.out.println(i + 1);
            }
        }
    }

}
