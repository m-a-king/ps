package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ5341 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        while (true) {
            int n = Integer.parseInt(bufferedReader.readLine());

            if (n == 0) {
                break;
            }

            stringBuilder.append(n * (n + 1) / 2).append("\n");
        }

        System.out.println(stringBuilder);
    }
}
