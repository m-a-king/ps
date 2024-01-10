package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;
import static java.lang.Math.min;

public class BJ2798 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = bufferedReader.readLine().split(" ");
        int cardCount = parseInt(input1[0]);

        int[] cards = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int target = parseInt(input1[1]);

        int maxRes = Integer.MIN_VALUE;

        for (int i = 0; i < cardCount - 2; i++) {
            for (int j = i + 1; j < cardCount - 1; j++) {
                for (int k = j + 1; k < cardCount; k++) {
                    int now = cards[i] + cards[j] + cards[k];
                    if (now <= target) {
                        maxRes = max(maxRes, now);
                    }
                }
            }
        }

        System.out.println(maxRes);
    }
}
