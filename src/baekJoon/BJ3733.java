package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ3733 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        String input;
        while ((input = bufferedReader.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(input, " ");

            int n = Integer.parseInt(stringTokenizer.nextToken()) + 1;
            int s = Integer.parseInt(stringTokenizer.nextToken());

            stringBuilder.append(s / n).append("\n");
        }

        System.out.println(stringBuilder);

    }
}
