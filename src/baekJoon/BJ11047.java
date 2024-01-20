package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ11047 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        int n = parseInt(input[0]);
        int target = parseInt(input[1]);
        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {

            int coin = parseInt(bufferedReader.readLine());
                coins[i] = coin;
        }

        int count = 0;
        int i = coins.length - 1;

        while (target != 0) {

            if (target >= coins[i]) {
                target -= coins[i];
                count++;
            } else if (target < coins[i]) {
                i--;
            }

        }

        System.out.println(count);

    }


}

