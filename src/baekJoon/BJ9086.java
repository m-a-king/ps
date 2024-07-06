package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ9086 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < n; i++) {
            String s = bufferedReader.readLine();

            char start = s.charAt(0);
            char end = s.charAt(s.length() - 1);

            System.out.println(start + "" + end);
        }

    }
}
