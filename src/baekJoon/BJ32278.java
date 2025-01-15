package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ32278 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(bufferedReader.readLine());

        if (N >= -32_768 && N <= 32_767) {
            System.out.println("short");
            return;
        }
        if (N >= -2_147_483_648L && N <= 2_147_483_647L) {
            System.out.println("int");
            return;
        }
        if (N >= -9_223_372_036_854_775_808L && N <= 9_223_372_036_854_775_807L) {
            System.out.println("long long");
        }
    }
}
