package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1074 {

    static int n;
    static int r;
    static int c;
    static int count = 0;


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        r = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());

        int size = (int) Math.pow(2, n);

        split(size, 0, 0);
    }

    private static void split(int size, int x, int y) {

        if (size == 2) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (x + i == r && y + j == c) {
                        System.out.println(count);
                    } else {
                        count++;
                    }
                }
            }

        } else {

            if (x <= r && r <= x + size && y <= c && c <= y + size) {
                size /= 2;

                split(size, x, y);
                split(size, x, y + size);
                split(size, x + size, y);
                split(size, x + size, y + size);
            } else {
                count += size * size;
            }
        }

    }
}
