package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class BJ10816 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int first = parseInt(bufferedReader.readLine());
        String[] f = bufferedReader.readLine().split(" ");

        Map<String, Integer> map = new HashMap<>();

        for (String x : f) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        int second = parseInt(bufferedReader.readLine());
        String[] s = bufferedReader.readLine().split(" ");
        StringBuilder stringBuilder = new StringBuilder();

        for (String x : s) {
            stringBuilder.append((map.getOrDefault(x, 0)));
            stringBuilder.append(" ");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        System.out.println(stringBuilder);
    }
}
