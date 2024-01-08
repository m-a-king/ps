package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ27866 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] word = bufferedReader.readLine().split("");
        int n = parseInt(bufferedReader.readLine());

        System.out.println(word[n-1]);

    }
}
