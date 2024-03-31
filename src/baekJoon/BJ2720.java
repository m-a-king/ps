package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2720 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        int[] unit = new int[4];
        unit[0] = 25;
        unit[1] = 10;
        unit[2] = 5;
        unit[3] = 1;

        StringBuilder stringBuilder = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            int change = Integer.parseInt(bufferedReader.readLine());
            int current = 0;
            int count = 0;
            while (true) {
                if (change >= unit[current]) {
                    change = change - unit[current];
                    count++;
                }
                if (change < unit[current]) {
                    current++;

                    stringBuilder.append(count).append(" ");
                    count = 0;
                    if (current == 4) {
                        break;
                    }
                }
            }
            stringBuilder.append("\n");

        }

        System.out.println(stringBuilder);

    }
}
