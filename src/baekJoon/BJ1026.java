package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1026 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        int[] a = new int[n];
        int[] b = new int[n];

        StringTokenizer stringTokenizer1 = new StringTokenizer(bufferedReader.readLine(), " ");
        StringTokenizer stringTokenizer2 = new StringTokenizer(bufferedReader.readLine(), " ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(stringTokenizer1.nextToken());
            b[i] = Integer.parseInt(stringTokenizer2.nextToken());
        }

        Arrays.sort(a);
        Arrays.sort(b);

        int res = 0;

        for (int i = 0; i < n; i++) {
            res += a[i] * b[n - i - 1];
        }

        System.out.println(res);
    }
}
