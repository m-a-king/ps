package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11054 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int[] input = new int[n];
        int[] upDp = new int[n];
        int[] downDp = new int[n];
        int max = 0;

        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(stringTokenizer.nextToken());
            upDp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (input[j] < input[i] && upDp[i] < upDp[j] + 1) {
                    upDp[i] = upDp[j] + 1;
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            downDp[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (input[j] < input[i] && downDp[i] < downDp[j] + 1) {
                    downDp[i] = downDp[j] + 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            max = Math.max(max, upDp[i] + downDp[i]-1);
        }

        System.out.println(max);
    }
}
