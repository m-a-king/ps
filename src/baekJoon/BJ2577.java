package baekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ2577 {

    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int value = 1;
        for (int i = 0; i < 3; i++) {
            value *= parseInt(bufferedReader.readLine());

        }

        String[] wordSplit = Integer.toString(value).split("");
        int[] numberCount = new int[10];

        for (String number : wordSplit) {
            numberCount[parseInt(number)]++;
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(numberCount[i]);


        }
    }
}
