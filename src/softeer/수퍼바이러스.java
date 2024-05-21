package softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수퍼바이러스 {
    static int remainder = 1000000007;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        long k = Long.parseLong(stringTokenizer.nextToken());
        long p = Long.parseLong(stringTokenizer.nextToken());
        long n = Long.parseLong(stringTokenizer.nextToken());


        // k*p를 n*10회 수행

        System.out.println(k * recursion(p, n * 10) % remainder);
    }

    private static long recursion(long p, long n) {
//        if (n == 0) {
//            return 1; // base^0 = 1
//        }

        if (n == 1) {
            return p;
        }
        if (n % 2 == 0) {
            long half = recursion(p, n / 2); // 두 번 구할 필요 없게
            return half * half % remainder;
        }

        return recursion(p, n - 1) * p % remainder;
    }
}
