package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ11718 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        String line = "";
        while((line = bufferedReader.readLine()) != null) {

            stringBuilder.append(line).append("\n");
        }

        System.out.println(stringBuilder);
    }
}
