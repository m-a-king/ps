package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ9498 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int x = parseInt(bufferedReader.readLine());

        if (x >= 90) {
            System.out.println("A");
        } else if (x >= 80) {
            System.out.println("B");
        } else if (x >= 70) {
            System.out.println("C");
        } else if (x >= 60) {
            System.out.println("D");
        } else {
            System.out.println("F");
        }


    }
}
