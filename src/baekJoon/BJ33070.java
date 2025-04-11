package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ33070 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        int[] states = new int[N];
        int[] requires = new int[N + 1];

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < K; i++) {
            requires[Integer.parseInt(stringTokenizer.nextToken())] = 1;
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            states[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            requires[i] += requires[i - 1];
        }
        System.out.println(Arrays.toString(requires));

        int min = 0;
        int max = 0;
        int answer = 0;

        for (final int state : states) {
            if (state == 0) {
                min++;
                max++;
                continue;
            }

            final int require = min == 0 ? 0 : requires[min - 1];

            if (requires[max] - require > 0) {
                max++;
            } else {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
