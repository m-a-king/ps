package baekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ2675 {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int count = parseInt(bufferedReader.readLine());

        for (int i = 0; i < count; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int repeat = parseInt(split[0]);
            String word = split[1];
            String[] wordSplit = word.split("");

            for (int j = 0; j < wordSplit.length; j++) {
                for (int k = 0; k < repeat; k++) {
                    System.out.print(wordSplit[j]);
                }
            }
            System.out.println();
        }

    }
}
