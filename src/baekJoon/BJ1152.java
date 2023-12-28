package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1152 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] word = bufferedReader.readLine().split(" ");
        int count = word.length;

        for (int i = 0; i < count; i++) {
            if (word[i] == "") {
                count--;
            }
        }


        System.out.println(count);
    }
}
