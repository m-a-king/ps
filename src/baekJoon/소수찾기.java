package baekJoon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 소수찾기 {

    static class Solution {

        static Set<Integer> generated = new HashSet<>();
        static int length;

        public int solution(String number) {
            int answer = 0;
            length = number.length();

            String[] numbers = new String[length];

            for (int i = 0; i < length; i++) {
                numbers[i] = String.valueOf(number.charAt(i));
            }

            for (int i = 1; i <= length; i++) {
                boolean[] visited = new boolean[length];
                generate(i, numbers, visited, "");
            }

            Set<Integer> prime = new HashSet<>();
            int max = 1;
            for (int i = 0; i < length; i++) {
                max *= 10;
            }

            boolean[] isPrime = new boolean[max];
            Arrays.fill(isPrime, true);

            isPrime[0] = isPrime[1] = false;

            for (int i = 2; i * i < max; i++) {
                if (isPrime[i]) {
                    for (int j = i * i; j < max; j += i) {
                        isPrime[j] = false;
                    }
                }
            }

            for (int i = 1; i < max; i++) {
                if (isPrime[i]) {
                    prime.add(i);
                }
            }

            generated.retainAll(prime);
            return generated.size();
        }

        private void generate(int targetLength, String[] numbers, boolean[] visited, String result) {
            if (result.length() == targetLength) {
                generated.add(Integer.parseInt(result));
                return;
            }

            for (int i = 0; i < length; i++) {
                if (visited[i]) {
                    continue;
                }
                visited[i] = true;
                generate(targetLength, numbers, visited, result + numbers[i]);
                visited[i] = false;
            }
        }
    }
}
