package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ10952 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] input = line.split(" ");

            int res = parseInt(input[0]) + parseInt(input[1]);

            if (res == 0) {
                return;
            }
            System.out.println(res);
        }

    }
}
