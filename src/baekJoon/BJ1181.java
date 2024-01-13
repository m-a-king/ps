package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BJ1181 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());

        Map<Integer, Set<String>> words = new HashMap<>();

        for (int i = 0; i < n; i++) {

            String word = bufferedReader.readLine();
            Integer length = word.length();

            Set<String> set = words.getOrDefault(length, new HashSet<>());
            set.add(word);
            words.put(length, set);
        }

        for (int i = 1; i <= 50; i++) {
            Set<String> set = words.getOrDefault(i, new HashSet<>());
            List<String> list = new ArrayList<>(set);
            Collections.sort(list);

            for (String word : list) {
                System.out.println(word);
            }
        }




    }
}
