package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BJ9095 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(bufferedReader.readLine());
        List<Integer> dp = new ArrayList<>();

        dp.add(0);
        dp.add(1);
        dp.add(2);
        dp.add(4);

        for (int i = 0; i < N; i++) {
            int number = parseInt(bufferedReader.readLine());

            for (int target = 4; target <= number; target++) {
                dp.add(target, dp.get(target - 1) + dp.get(target - 2) + dp.get(target - 3));
            }

            System.out.println(dp.get(number));

        }
    }
}
