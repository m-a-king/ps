package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class BJ11659 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = bufferedReader.readLine().split(" ");
        int n = parseInt(nm[0]);
        int m = parseInt(nm[1]);
        int[] numbers = new int[n+1];

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            numbers[i] = numbers[i-1] + Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int i = 0; i < m; i++) {
            String[] se = bufferedReader.readLine().split(" ");
            int s = parseInt(se[0]);
            int e = parseInt(se[1]);

            System.out.println(numbers[e] - numbers[s-1]);
        }
    }
}
