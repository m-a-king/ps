package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BJ20437 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        while (t-- > 0) {
            List<List<Integer>> alpPoss = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                alpPoss.add(new ArrayList<>());
            }

            String input = bufferedReader.readLine();
            for (int i = 0; i < input.length(); i++) {
                alpPoss.get(input.charAt(i) - 97).add(i);
            }

//            System.out.println(alpPoss);

            int k = Integer.parseInt(bufferedReader.readLine()) - 1;
            int minDiff = 10001;
            int maxDiff = 0;
//            boolean isFoundMinDiff = false;
//            boolean isFoundMaxDiff = false;

            for (int i = 0; i < 26; i++) {
                List<Integer> alpPos = alpPoss.get(i);

                if (alpPos.size() < k) continue;

                for (int p = 0; p < alpPos.size() - k; p++) {
                    minDiff = Math.min(minDiff, alpPos.get(p + k) - alpPos.get(p));
                    maxDiff = Math.max(maxDiff, alpPos.get(p + k) - alpPos.get(p));
                }

//                for (int p = 0; p < alpPos.size() - k; p++) {
//                    if (isFoundMinDiff) break;
//                    minDiff = Math.min(minDiff, alpPos.get(p + k) - alpPos.get(p));
//                    if (minDiff == k) isFoundMinDiff = true;
//                }
//
//                for (int p = 0; p < alpPos.size() - k; p++) {
//                    if (isFoundMaxDiff) break;
//                    maxDiff = Math.max(maxDiff, alpPos.get(p + k) - alpPos.get(p));
//                    if (maxDiff == input.length()) isFoundMaxDiff = true;
//                }

            }

            if (minDiff == 10001 && maxDiff == 0) stringBuilder.append(-1).append("\n");
            else stringBuilder.append(minDiff + 1).append(" ").append(maxDiff + 1).append("\n");

        }

        System.out.println(stringBuilder);

    }
}
