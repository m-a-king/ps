package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ8958 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int count = parseInt(bufferedReader.readLine());
        int[] point = new int[count];

        for (int i = 0; i < count; i++) {
            String[] OX = bufferedReader.readLine().split("");
            int repeat = 0;

            for (String ox : OX) {

                if (ox.equals("O")) {
                    repeat++;
                    point[i] += repeat;
                } else if (ox.equals("X")) {
                    repeat = 0;
                }
            }
        }

        for (int p : point) {
            System.out.println(p);
        }

    }
}
