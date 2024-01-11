package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;

public class BJ11050 {
    static int[] factorialCache;
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        int a = parseInt(input[0]);
        int b = parseInt(input[1]);

        factorialCache = new int[a + 1];
        Arrays.fill(factorialCache, -1);

        if (a == 0 || b == 0) {
            System.out.println("1");
            return;
        }

        System.out.println(factorial(a) / (factorial(b) * factorial(a - b)));


    }

    private static int factorial(int i) {
        if (i <= 1) {
            return 1;
        }

        if (factorialCache[i] != -1) {
            return factorialCache[i];
        }

        factorialCache[i] = i * factorial(i - 1);

        return factorialCache[i];
    }
}
