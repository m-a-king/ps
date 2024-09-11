package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ30804 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] fruits = new int[n];

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        for (int i = 0; i < n; i++) {
            fruits[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        Map<Integer, Integer> counter = new HashMap<>();
        int answer = 0;

        int p1 = 0;

        for (int p2 = 0; p2 < n; p2++) {
            counter.put(fruits[p2], counter.getOrDefault(fruits[p2], 0) + 1);

            while (counter.size() == 3) {
                counter.put(fruits[p1], counter.getOrDefault(fruits[p1], 0) - 1);
                if (counter.get(fruits[p1]) == 0) {
                    counter.remove(fruits[p1]);
                }
                p1++;
            }

            answer = Math.max(answer, p2 - p1 + 1);

        }
        System.out.println(answer);

    }
}
