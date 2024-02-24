package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ18870 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        int[] originalNumbers = new int[n];
        Integer[] sortedNumbers = new Integer[n];

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        for (int i = 0; i < n; i++) {
            originalNumbers[i] = Integer.parseInt(stringTokenizer.nextToken());
            sortedNumbers[i] = originalNumbers[i];
        }

        Arrays.sort(sortedNumbers);
        Set<Integer> set = new LinkedHashSet<>(Arrays.asList(sortedNumbers));

        Map<Integer, Integer> map = new HashMap<>();

        int i = 0;
        for (int number : set) {
            map.put(number, i);
            i++;
        }

        StringBuilder sb = new StringBuilder();
        for (int num : originalNumbers) {
            sb.append(map.get(num)).append(" ");
        }

        System.out.println(sb);
    }
}
