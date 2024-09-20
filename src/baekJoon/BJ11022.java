package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11022 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());

            stringBuilder.append("Case #").append(i+1).append(": ").append(a).append(" + ").append(b).append(" = ").append(a + b).append("\n");

        }

        System.out.println(stringBuilder);
    }
}
