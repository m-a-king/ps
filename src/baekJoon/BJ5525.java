package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ5525 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int length = Integer.parseInt(bufferedReader.readLine());
        String s = bufferedReader.readLine();

        int count = 0; // "OI" 패턴의 연속된 횟수
        int result = 0; // "IOI" 패턴의 총 개수

        for (int i = 1; i < length - 1; i++) {

            if ((s.charAt(i - 1) == 'I') && (s.charAt(i) == 'O') && (s.charAt(i + 1) == 'I')) {
                i++;
                count++;

                if (count == n) {
                    result++;
                    count--;
                }

            } else {
                count = 0;
            }

        }

        result += count / n;

        System.out.println(result);


    }

}
