package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Long.parseLong;

public class BJ1629 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] ABC = bufferedReader.readLine().split(" ");

        long A = parseLong(ABC[0]);
        long B = parseLong(ABC[1]);
        long C = parseLong(ABC[2]);

        System.out.println(powABC(A, B, C));
    }

    private static long powABC(long a, long b, long c) {
        if (b == 0) {
            return 1;
        }

        long half = powABC(a, b / 2, c);
        long res = half * half % c;
        if (b % 2 == 1) {
            res = res * a % c;
        }

        return res;



    }
}
