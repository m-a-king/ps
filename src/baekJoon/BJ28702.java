package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ28702 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 3; i++) {
            String line = bufferedReader.readLine();

            if (line.endsWith("z")) {
                continue;
            }

            int num = Integer.parseInt(line) + 4 - i;

            System.out.println(
                    num % 3 == 0 && num % 5 == 0 ? "FizzBuzz" :
                            num % 3 == 0 ? "Fizz" :
                                    num % 5 == 0 ? "Buzz" : num);
            return;

        }


    }
}
