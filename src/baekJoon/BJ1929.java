package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BJ1929 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        int start = parseInt(input[0]);
        int end = parseInt(input[1]);

        boolean[] isPrime = new boolean[end + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 2; i <= Math.sqrt(end); i++) {
            if (isPrime[i]) {
//                int firstMultipleInRange = ((start + i - 1) / i) * i;
                int nextMultiple = i * i;
                for (int j = nextMultiple; j <= end; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = start; i <= end; i++) {
            if (isPrime[i]) {
                stringBuilder.append(i).append("\n");
            }
        }

        System.out.println(stringBuilder.toString().trim());
    }


}
