package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Toss_멋쟁이숫자 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String n = bufferedReader.readLine();

        int answer = 0;
        for (int i = 0; i < n.length()-2; i++) {

            if (n.charAt(i) == n.charAt(i + 1) && n.charAt(i + 1) == n.charAt(i + 2)) {
                System.out.println(n.charAt(i));
                answer = Math.max(answer, n.charAt(i) - '0');
                System.out.println(answer);
            }

        }

        System.out.println(answer);

    }
}
