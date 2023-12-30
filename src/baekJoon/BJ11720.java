package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BJ11720 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int count = parseInt(bufferedReader.readLine());

        String[] numbers = bufferedReader.readLine().split("");
        int res = 0;
        for (int i = 0; i < count; i++) {
            res += parseInt(numbers[i]);
        }

        System.out.println(res);
    }
}

