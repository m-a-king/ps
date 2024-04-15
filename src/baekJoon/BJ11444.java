package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BJ11444 {

    static int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(bufferedReader.readLine());


        System.out.println(fib(n));


    }

    // 피보나치 수를 계산하는 메인 함수
    public static long fib(long n) {
        if (n == 0) return 0;
        long[][] F = {{1, 1}, {1, 0}};
        long[][] result = matrixPow(F, n - 1);
        return result[0][0];
    }

    // 행렬 거듭제곱을 계산하는 함수
    public static long[][] matrixPow(long[][] matrix, long n) {
        long[][] result = {{1, 0}, {0, 1}}; // 단위 행렬
        while (n > 0) {
            if (n % 2 == 1) result = matrixMultiply(result, matrix);
            n /= 2;
            matrix = matrixMultiply(matrix, matrix);
        }
        return result;
    }

    // 두 행렬을 곱하는 함수
    public static long[][] matrixMultiply(long[][] a, long[][] b) {
        long[][] c = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return c;
    }
}
