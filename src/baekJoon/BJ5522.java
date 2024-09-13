package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ5522 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int answer = 0;

        for (int i = 0; i < 5; i++) {
            int n = Integer.parseInt(bufferedReader.readLine());
            answer += n;
        }

        System.out.println(answer);

    }
}
