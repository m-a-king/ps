package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ1003 {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tc = parseInt(bufferedReader.readLine());

        int[][] count = new int[41][2];

        count[0][0] = 1;
        count[0][1] = 0;
        count[1][0] = 0;
        count[1][1] = 1;

        for (int i = 2; i <= 40; i++) {
            for (int j = 0; j < 2; j++) {
                count[i][j] = count[i - 2][j] + count[i - 1][j];
            }
        }

        for (int t = 0; t < tc; t++) {

            int number = parseInt(bufferedReader.readLine());

            System.out.println(count[number][0] + " " + count[number][1]);
        }
    }


}
