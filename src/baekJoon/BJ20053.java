package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ20053 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        while (t-- > 0) {
            int n = Integer.parseInt(bufferedReader.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                int num = Integer.parseInt(stringTokenizer.nextToken());

                if (min > num) {
                    min = num;
                }

                if (max < num) {
                    max = num;
                }
            }

            stringBuilder.append(min).append(" ").append(max).append("\n");
        }

        System.out.println(stringBuilder);

    }
}
