package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ32278 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(bufferedReader.readLine());

        if (N >= Short.MIN_VALUE && N <= Short.MAX_VALUE) {
            System.out.println("short");
            return;
        }
        if (N >= Integer.MIN_VALUE && N <= Integer.MAX_VALUE) {
            System.out.println("int");
            return;
        }
        System.out.println("long long");
    }
}
