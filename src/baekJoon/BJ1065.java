package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1065 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int count = 0;

        for (int i = 1; i <= n; i++) {

            String stringI = Integer.toString(i);
            int iLength = stringI.length();
            if (iLength <= 2) {
                count++;
                continue;
            }

            int diff = stringI.charAt(1) - stringI.charAt(0);

            for (int j = 2; j < iLength; j++) {
                if (diff != stringI.charAt(j) - stringI.charAt(j - 1)) {
                    break;
                }
                if (j == iLength - 1) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
