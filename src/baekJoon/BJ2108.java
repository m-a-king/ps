package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Math.round;

public class BJ2108 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());
        int[] numbers = new int[n];
        int total = 0;
        Map<Integer, Integer> frequent = new HashMap<>();

        for (int i = 0; i < n; i++) {
            numbers[i] = parseInt(bufferedReader.readLine());
            total += numbers[i];
            frequent.put(numbers[i], frequent.getOrDefault(numbers[i], 0) + 1);
        }

        Arrays.sort(numbers);

        int average = (int) round((double) total / n);
        int mid = numbers[n / 2];

        System.out.println(average);
        System.out.println(mid);

        if (n == 1) {
            System.out.println(numbers[0]);
        } else {


            int maxFreq = 0;
            for (int freq : frequent.values()) {
                if (freq > maxFreq) {
                    maxFreq = freq;
                }
            }

            List<Integer> FreqNumbers = new ArrayList<>();

            for (Map.Entry<Integer, Integer> entry : frequent.entrySet()) {
                if (entry.getValue() == maxFreq) {
                    FreqNumbers.add(entry.getKey());
                }
            }

            Collections.sort(FreqNumbers);

            if (FreqNumbers.size() > 1) {
                System.out.println(FreqNumbers.get(1));

            } else {
                System.out.println(FreqNumbers.get(0));
            }
        }

        System.out.println(numbers[numbers.length - 1] - numbers[0]);

    }
}
