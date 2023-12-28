package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ2439 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int count = parseInt(bufferedReader.readLine());


        for (int i = 1; i <= count; i++) {
            for (int space = i; space < count; space++) {
                System.out.print(" ");
            }

            for (int star = 1; star <= i; star++) {

                System.out.print("*");
            }
            System.out.println();
        }
    }
}
