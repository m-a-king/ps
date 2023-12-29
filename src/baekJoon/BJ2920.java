package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2920 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int[] sounds = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int temp = 0;
        String expected = "";

        for (int sound : sounds) {
            if (temp == 0) {
                temp = sound;
            } else {
                if (sound - temp == 1 && (expected == "" || expected == "ascending")) {
                    expected = "ascending";
                } else if (sound - temp == -1 && (expected == "" || expected == "descending")) {
                    expected = "descending";
                } else {
                    expected = "mixed";
                    break;
                }

                temp = sound;
            }
        }

        System.out.println(expected);
    }
}
