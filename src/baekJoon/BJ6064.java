package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ6064 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < t; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());

            int lcm = m * n / gcd(m, n);

            int k = x;

            while (true) {
                if (k % n == y % n) {
                    System.out.println(k);
                    break;
                }
                k += m;
                if (k > lcm) {
                    System.out.println(-1);
                    break;
                }
            }
        }
    }

    private static int gcd(int m, int n) {

        while (n != 0) {
            int temp = n;
            n = m % n;
            m = temp;
        }

        return m;
    }
}
