package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ10950 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());

        for (int i = 0; i < n; i++) {
            String[] input = bufferedReader.readLine().split(" ");

            System.out.println(parseInt(input[0]) + parseInt(input[1]));
        }
    }
}
