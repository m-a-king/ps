package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1300 {

    static int n;
    static int k;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        k = Integer.parseInt(bufferedReader.readLine());

        System.out.println(binarySearch(1, k));

    }

    private static long binarySearch(long start, long end) {
        long mid = 0;
        long count = 0;

        while (start <= end) {
            mid = (start + end) / 2; // 이분탐색 기준 (짐작하는 b[k] 값)

            if (start == end) {
                return mid;
            }

            count = countSmallNum(mid);

            // b 배열에서 현재 mid 이하인 값의 개수가 찾고 있는 k 이상일때
            if (count >= k) {
                end = mid; // 짐작하는 값을 작게
            }

            // b 배열에서 현재 mid 이하인 값의 개수가 찾고 있는 k 미만
            else {
                start = mid + 1; // 짐작하는 값을 크게
            }

        }

        return mid;
    }

    // 주어진 mid(limit)을 기준으로
    // 전체 배열에서 그 수까지의 개수
    private static long countSmallNum(long limit) {
        long count = 0;
        long tempLimit = 0;

//        for (int i = 1; i <= n; i++) {
//            tempLimit = Math.min(n * i, limit);
//            count += tempLimit / i;
//        }

        for (int i = 1; i <= n; i++) {
            count += Math.min(limit / i, n);
        }

        return count;
    }
}