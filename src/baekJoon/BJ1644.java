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

        // true : 소수가 아님
        // false : 소수 (기본값)
        boolean[] isntPrime = new boolean[n + 1];

        // 0, 1은 소수가 아님
        isntPrime[0] = isntPrime[1] = true;

        // i를 2부터 √n까지 반복
        for (int i = 2; i * i <= n; i++) {
            // i가 소수(혹은 기본값)라면
            if (!isntPrime[i]) {
                // i의 배수는 모두 소수가 아니다
                // j를 i*i부터 시작하여 i씩 증가시키며 소수가 아니라고 표시
                for (int j = i * i; j <= n; j += i) {
                    isntPrime[j] = true;
                }
            }
        }

        // 2는 소수다.
        primes.add(2);

        // 3부터 홀수를 체크하며 소수 리스트를 채운다
        // 간단히 연속합을 구하기 위해서
        for (int i = 3; i <= n; i += 2) {
            if (!isntPrime[i]) {
                primes.add(i);
            }
        }

//        for (int prime : primes) {
//            System.out.println(prime);
//        }

        // 0(start)번째 소수부터 0(end)번째 소수까지 더한다.
        calcContinuousSum(0, 0);

        System.out.println(count);

    }

    private static void calcContinuousSum(int start, int end) {
        int sum = 0;

//        System.out.println("primes = " + primes.size());

        // end 가 소수 리스트의 끝을 넘어서지 않았다면
        while (end < primes.size()) {

            // 소수의 연속합이 목표보다 작다면
            // 연속합에 end를 더하고 end++
            if (sum < n) {
                sum += primes.get(end++);
                continue;
            }
            // 소수의 연속합이 목표보다 크다면
            // 연속합에 start를 뺴고 start++
            if (sum > n) {
                sum -= primes.get(start++);
                continue;
            }
            // 소수의 연속합이 목표와 같다면
            // 연속합에 end를 더하고 end++ (다음 연속합도 구하기 위해서)
            // count(정답)++
            if (sum == n) {
                count++;
                sum += primes.get(end++);
                continue;
            }
        }

        // 위 반복을 완료한다면
        // 소수 리스트의 마지막과 목표값이 같은지 비교
        // end++를 해서 마지막 while문의 조건에 만족하지 못해서 예외처리..
        if (n == primes.get(primes.size() - 1)) {
            count++;
        }
    }
}
