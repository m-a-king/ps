package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ1436 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());
        int count = 0;
        int number = 0;
        while (true) {
            if (count == n) {
                break;
            }
            number++;
            if (String.valueOf(number).contains("666")) {
                count++;
            }
        }

        System.out.println(number);
    }
}
