package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BJ1644 {

    static List<Integer> primes = new ArrayList<>();
    static int n;
    static int count = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());

        boolean[] isntPrime = new boolean[n + 1];
        isntPrime[0] = isntPrime[1] = true;

        for (int i = 2; i * i <= n; i++) {
            if (!isntPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isntPrime[j] = true;
                }
            }
        }

        primes.add(2);
        for (int i = 3; i <= n; i += 2) {
            if (!isntPrime[i]) {
                primes.add(i);
            }
        }

        for (int prime : primes) {
            System.out.println(prime);
        }


        calcContinuousSum(0, 0);

        System.out.println(count);

    }

    private static void calcContinuousSum(int start, int end) {
        int sum = 0;

        System.out.println("primes = " + primes.size());

        while (end < primes.size()) {

            if (sum < n) {
                sum += primes.get(end++);
                continue;
            }
            if (sum > n) {
                sum -= primes.get(start++);
                continue;
            }
            if (sum == n) {
                count++;
                sum += primes.get(end++);
                continue;
            }
        }

        if (n == primes.get(primes.size() - 1)) {
            count++;
        }
    }
}
