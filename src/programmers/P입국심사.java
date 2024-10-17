package programmers;

import java.util.Arrays;

public class P입국심사 {

    static int n;
    static int[] times;

    public long solution(int nn, int[] timess) {
        times = timess;
        n = nn;
        Arrays.sort(times);

        long left = 0;
        long right = (long) n * times[0];

        while (left < right) {

            long mid = (left + right) / 2;

            if (calc(mid) < n) left = mid + 1;
            else right = mid;
        }

        return left;
    }

    private static long calc(long time) {
        long count = 0;
        for (int t : times) {
            count += time / t;
        }

        return count;
    }

}
