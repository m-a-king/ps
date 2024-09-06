package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ30802 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer1 = new StringTokenizer(bufferedReader.readLine(), " ");
        StringTokenizer stringTokenizer2 = new StringTokenizer(bufferedReader.readLine(), " ");
        int t = Integer.parseInt(stringTokenizer2.nextToken());
        int p = Integer.parseInt(stringTokenizer2.nextToken());

        int ans1 = 0;

        for (int i = 0; i < 6; i++) {
            int curr = Integer.parseInt(stringTokenizer1.nextToken());
            ans1 += curr % t == 0 ? curr / t : curr / t + 1;
        }
        System.out.println(ans1);

        System.out.println(n / p + " " + n % p);
    }
}
