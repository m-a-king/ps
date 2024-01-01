package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ2438 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int x = parseInt(bufferedReader.readLine());

        for (int i = 0; i < x; i++) {
            for(int j=0; j<=i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }


    }
}
