package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ10250 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int repeat = parseInt(bufferedReader.readLine());

        for (int i = 0; i < repeat; i++) {
            String[] line = bufferedReader.readLine().split(" ");

            int H = Integer.parseInt(line[0]);
            int W = Integer.parseInt(line[1]);
            int N = Integer.parseInt(line[2]);

            int vertical = (N % H == 0) ? H : N % H;
            int horizontal = (N % H == 0) ? (N / H) : (N / H) + 1;

            if (horizontal > 9) {
                System.out.println(vertical + "" + horizontal);
            } else {
                System.out.println(vertical + "0" + horizontal);

            }
        }
    }
}

