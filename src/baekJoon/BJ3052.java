package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class BJ3052 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Integer[] remainder = new Integer[10];

        for (int i = 0; i < 10; i++) {
            remainder[i] = parseInt(bufferedReader.readLine()) % 42;
        }

        Set<Integer> result = new HashSet<>(List.of(remainder));

        System.out.println(result.size());
    }
}
