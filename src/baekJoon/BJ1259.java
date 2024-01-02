package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1259 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            String line = bufferedReader.readLine();
            String[] number = line.split("");

            if (line.equals("0")) {
                break;
            }

            int n = number.length;
            int count = n / 2;


            for (int j = 0; j < n / 2; j++) {
                if (number[j].equals(number[n - 1 - j])) {
                    count--;
                }
            }
            if (count == 0) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }

    }
}
