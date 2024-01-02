package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ2753 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int year = parseInt(bufferedReader.readLine());

        Boolean x = (year % 100 != 0);
        Boolean y = (year % 4 == 0);
        Boolean z = (year % 400 == 0);

        if (x && y) {
            System.out.println("1");
        } else if (z) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }
}

