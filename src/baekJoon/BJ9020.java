package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ9020 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(bufferedReader.readLine());

        boolean[] isPrime = new boolean[10001];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i < isPrime.length; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < isPrime.length; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        while (tc-- > 0) {
            int goldbachNum = Integer.parseInt(bufferedReader.readLine());

            int first = goldbachNum / 2;
            int second = goldbachNum / 2;

            while (first > 0) {
                if (isPrime[first] && isPrime[second]) {
                    stringBuilder.append(first).append(" ").append(second).append("\n");
                    break;
                }
                first--;
                second++;
            }
        }

        System.out.print(stringBuilder);
    }
}