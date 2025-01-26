package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ24500 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        long N = Long.parseLong(bufferedReader.readLine());
        process(N, result);

        System.out.println(result);
    }

    private static void process(long N, StringBuilder result) {
        int binaryLength = Long.toBinaryString(N).length();
        long mask = (1L << binaryLength) - 1;
        long flipped = N ^ mask;

        if (flipped == 0) {
            result.append(1)
                    .append("\n")
                    .append(N);
            return;
        }

        result.append(2)
                .append("\n")
                .append(flipped)
                .append("\n")
                .append(N);
    }
}

// 1000 -> 1000, 111
// 111 -> 111
// 110 -> 110, 1