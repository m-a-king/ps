package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class BJ1620 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        int pocketMonCount = parseInt(input[0]);
        int problemCount = parseInt(input[1]);

        Map<String, Integer> nameToIndex = new HashMap<>();
        Map<Integer, String> indexToName = new HashMap<>();

        for (int i = 1; i <= pocketMonCount; i++) {
            String name = bufferedReader.readLine();
            nameToIndex.put(name, i);
            indexToName.put(i, name);
        }

        for (int i = 0; i < problemCount; i++) {
            String problem = bufferedReader.readLine();

            if (Character.isDigit(problem.charAt(0))) {
                System.out.println(indexToName.get(parseInt(problem)));
            } else {
                System.out.println(nameToIndex.get(problem));

            }
        }
    }
}
