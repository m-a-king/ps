package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class BJ9375 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            int x = parseInt(bufferedReader.readLine());
            Map<String, Integer> cMap = new HashMap<>();

            for (int j = 0; j < x; j++) {
                String[] cloth = bufferedReader.readLine().split(" ");
                cMap.put(cloth[1], cMap.getOrDefault(cloth[1], 0) + 1);
            }

            int ans = 1;
            for (int value : cMap.values()) {
                ans *= (value + 1);

            }

            System.out.println(ans-1);
        }
    }
}
