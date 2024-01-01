package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ1330 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int[] x = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (x[0] < x[1]) {
            System.out.println("<");
        } else if (x[0] > x[1]) {
            System.out.println(">");
        } else {
            System.out.println("==");
        }
    }
}
