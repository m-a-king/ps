package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ2292 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int target = parseInt(bufferedReader.readLine());

        int now = 1;
        int count = 1;

        if (target == 1) {
            System.out.println("1");
            return;
        }

        while (true) {

            now += count*6;
            count++;

            if (now >= target) {
                System.out.println(count);
                break;
            }

        }
    }
}
