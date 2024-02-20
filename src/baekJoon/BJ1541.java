package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1541 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] first = bufferedReader.readLine().split("-");
        int[] second = new int[first.length];
        int total = 0;

        for (int i = 0; i < first.length; i++) {
            String[] firstSplit = first[i].split("\\+");

            for (String s : firstSplit) {
                second[i] += Integer.parseInt(s);
            }

            total -= second[i];
        }

        total += 2*second[0];
        System.out.println(total);
    }
}
