package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1072 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int X = Integer.parseInt(stringTokenizer.nextToken());
        int Y = Integer.parseInt(stringTokenizer.nextToken());

        int origin = getPercent(Y, X);
        if (origin >= 99) {
            System.out.println(-1);
            return;
        }

        int left = 1;
        int right = X;

        while (left < right) {
            int mid = (left + right) / 2;

            int expect = getPercent(Y + mid, X + mid);

            if (expect <= origin) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        System.out.println(left);
    }

    private static int getPercent(int Y, int X) {
        return (int) ((long) Y * 100 / X);
    }
}
