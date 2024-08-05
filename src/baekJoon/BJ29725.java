package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ29725 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int res = 0;

        for (int i = 0; i < 8; i++) {
            String input = bufferedReader.readLine();
            for (int j = 0; j < 8; j++) {
                switch (input.charAt(j)) {
                    case 'p' -> res += -1;
                    case 'P' -> res += 1;
                    case 'n', 'b' -> res += -3;
                    case 'N', 'B' -> res += 3;
                    case 'r' -> res += -5;
                    case 'R' -> res += 5;
                    case 'q' -> res += -9;
                    case 'Q' -> res += 9;
                }
            }
        }

        System.out.println(res);

    }
}
