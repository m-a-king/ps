package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ13172 {

    private static final int MOD = 1_000_000_007;

    private static final class Dice {
        private final int numberOfSides;
        private final int totalSum;

        private Dice(int numberOfSides,
                     int totalSum) {
            this.numberOfSides = numberOfSides;
            this.totalSum = totalSum;
        }

        public long getModAverage() {
            return (totalSum * modInverse(numberOfSides)) % MOD;
        }

        private long modInverse(int a) {
            return modularExponentiation(a, MOD - 2);
        }

        private long modularExponentiation(long base, long exp) {
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

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int diceCount = Integer.parseInt(bufferedReader.readLine());
        long totalSum = 0;

        for (int diceIdx = 0; diceIdx < diceCount; diceIdx++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int side = Integer.parseInt(stringTokenizer.nextToken());
            int sum = Integer.parseInt(stringTokenizer.nextToken());

            Dice dice = new Dice(side, sum);
            totalSum = (totalSum + dice.getModAverage()) % MOD;
        }

        System.out.println(totalSum);

    }
}
