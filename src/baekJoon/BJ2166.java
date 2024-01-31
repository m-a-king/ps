package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2166 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        long beforeX = 0;
        long beforeY = 0;
        long firstX = 0;
        long firstY = 0;

        long res1 = 0;
        long res2 = 0;

        for (int i = 0; i < N; i++) {
            String[] xy = bufferedReader.readLine().split(" ");
            long currentX = Long.parseLong(xy[0]);
            long currentY = Long.parseLong(xy[1]);

            if (i == 0) {
                firstX = currentX;
                firstY = currentY;
                beforeX = currentX;
                beforeY = currentY;
                continue;
            }

            res1 += beforeX * currentY;
            res2 += beforeY * currentX;

            beforeX = currentX;
            beforeY = currentY;
        }

        res1 += beforeX * firstY;
        res2 += beforeY * firstX;


        System.out.println(String.format("%.1f", (Math.abs(res1 - res2) / 2.0)));
    }
}
