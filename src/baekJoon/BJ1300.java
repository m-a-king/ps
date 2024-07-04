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

        while (start < end) {
            mid = (start + end) / 2; // 이분탐색 기준 (짐작하는 b[k] 값)
            count = countSmallNum(mid);


            // 카운트가 찾으려는 k번째 인덱스와 같거나
            // 카운트가 찾으려는 k번째 인덱스를 넘어섬
            if (count >= k) {
                end = mid; // 짐작하는 값을 작
            } else {
                start = mid + 1; // 짐작하는 값을 크게}
            }

        }

        return start;
    }

    // 주어진 mid(limit)을 기준으로
    // 전체 배열에서 그 수까지의 개수
    private static long countSmallNum(long limit) {
        long count = 0;

//        long tempLimit = 0;
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

// 3
// 7

// 1  2  3  4  5  6
// 2  4  6  8 10 12
// 3  6  9 12 15 18
// 4  8 12 16 20 24
// 5 10 15 20 25 30
// 6 12 18 24 30 36

