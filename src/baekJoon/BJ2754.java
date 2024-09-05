package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2754 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = bufferedReader.readLine();

        if (line.equals("F")) {
            System.out.println(0.0);
            return;
        }

        char grade = line.charAt(0);
        char pm = line.charAt(1);

        double res = 0;

        if (grade == 'A') {
            res = 4;
        } else if (grade == 'B') {
            res = 3;
        } else if (grade == 'C') {
            res = 2;
        } else if (grade == 'D') {
            res = 1;
        }

        if (pm == '0') {
            System.out.println(res);
        } else if (pm == '+') {
            System.out.println(res + 0.3);
        } else {
            System.out.println(res - 0.3);
        }
    }
}
