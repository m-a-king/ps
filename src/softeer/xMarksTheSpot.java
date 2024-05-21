package softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class xMarksTheSpot {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            String s = stringTokenizer.nextToken();
            String t = stringTokenizer.nextToken();

            int idx=0;
            for (; idx < s.length(); idx++) {
                if (s.charAt(idx) == 'x' || s.charAt(idx) == 'X') {
                    break;
                }
            }
            stringBuilder.append(Character.toUpperCase(t.charAt(idx)));
        }

        System.out.println(stringBuilder);
    }
}
