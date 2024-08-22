package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ3079 {

    static long[] times;
    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        times = new long[n];
        for (int i = 0; i < n; i++) {
            times[i] = Long.parseLong(bufferedReader.readLine());
        }

        Arrays.sort(times);

        long start = 0;
        long end = m * times[n - 1];

        while (start < end) {
            long mid = (start + end) / 2;

            long res = calc(mid);

            if(res < m) start = mid + 1;
            else end = mid;
        }

        System.out.println(start);

    }

    private static long calc(long mid) {
        long res = 0;
        for (long time : times) {
            res += mid / time;
            if (res > m) {
                return res; // mid 작아져야한다.
            }
        }

        return res;
    }
}
