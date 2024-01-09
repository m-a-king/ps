package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ2609 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        int a = parseInt(input[0]);
        int b = parseInt(input[1]);

        for (int i = a; i >= 0; i--) {
            if (a % i == 0 && b % i == 0) {
                System.out.println(i);
                break;
            }
        }

        int i = 1;
        int j = 1;
        while (true) {
            if (a * i == b * j) {
                System.out.println(a * i);
                break;
            } else if (a * i > b * j) {
                j++;
            } else {
                i++;
            }
        }

    }
}
