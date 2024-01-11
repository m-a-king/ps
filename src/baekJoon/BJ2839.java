package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ2839 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int target = parseInt(bufferedReader.readLine());

        int start = target / 5;

        while (true) {
            int now = target - start * 5;
            if (start < 0) {
                System.out.println("-1");
                return;
            } else if (now % 3 == 0) {
                System.out.println(start + now / 3);
                return;
            } else {
                start--;
            }
        }


    }
}
