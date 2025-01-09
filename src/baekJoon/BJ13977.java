package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ13977 {

    /*
    * 주어진 최대값 (400,000)을 활용해 미리 팩토리얼 값을 전부 계산해둘 수 있음.
    * 최적화를 위해서 리스트 대신 배열 활용 가능
    * */
    private static class FactorialCalculator {

        private final int MOD;
        private final List<Integer> result = new ArrayList<>();

        public FactorialCalculator(int MOD) {
            this.MOD = MOD;
            result.add(1);
            result.add(1);
        }

        public int getOrCalc(int target) {
            while (result.size() - 1 < target) {
                result.add((int) (((long) result.get(result.size() - 1) * result.size()) % MOD));
            }
            return result.get(target);
        }
    }

    /*
    * 주어진 최댓값 (400,000)을 활용해 미리 최대값 팩토리얼의 모듈러 역원을 구한 뒤,
    * 최대값에서 내려오면서 각 팩토리얼의 역원을 계산해 배열에 저장할 수 있음.
    * 해시 맵에 저장하는 캐싱 기법도 가능하긴 하다.
    * */
    public static class ModularInverseCalculator {

        private final int MOD;

        public ModularInverseCalculator(int MOD) {
            this.MOD = MOD;
        }

        public long calc(long a) {
            return power(a, MOD - 2); // 페르마의 소정리
        }

        private long power(long base, long exp) {
            long result = 1;

            while (exp > 0) {
                if (exp % 2 == 1) {
                    result = (result * base) % MOD;
                }
                base = (base * base) % MOD;
                exp /= 2;
            }

            return result;
        }
    }

    public static void main(String[] args) throws IOException {

        final int MOD = 1_000_000_007;

        FactorialCalculator factorialCalculator = new FactorialCalculator(MOD);
        ModularInverseCalculator modularInverseCalculator = new ModularInverseCalculator(MOD);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder results = new StringBuilder();

        int tc = Integer.parseInt(bufferedReader.readLine());

        while (tc-- > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int N = Integer.parseInt(stringTokenizer.nextToken());
            int K = Integer.parseInt(stringTokenizer.nextToken());

            if (K == 0) {
                results.append(1).append("\n");
                continue;
            }

            int N_factorial = factorialCalculator.getOrCalc(N);
            int K_factorial = factorialCalculator.getOrCalc(K);
            int N_minus_K_factorial = factorialCalculator.getOrCalc(N - K);

            long modInv_N_minus_K_factorial = modularInverseCalculator.calc(N_minus_K_factorial);
            long modInv_K_factorial = modularInverseCalculator.calc(K_factorial);

            long result = N_factorial * modInv_N_minus_K_factorial % MOD;
            result = result * modInv_K_factorial % MOD;

            results.append(result).append("\n");
        }

        System.out.println(results);
    }
}
