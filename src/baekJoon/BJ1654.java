package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Long.parseLong;

public class BJ1654 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] split = bufferedReader.readLine().split(" ");
        long count = parseLong(split[0]);
        long need = parseLong(split[1]);

        long[] cable = new long[(int) count];
        long totalCM = 0;

        for (int i = 0; i < count; i++) {
            cable[i] = parseLong(bufferedReader.readLine());
            totalCM += cable[i];
        }

        long maxCM = totalCM / need;
        long minCM = 1;
        long bestLength = 1;

        while (minCM <= maxCM) {
            long midCM = (maxCM + minCM) / 2;
            long totalCutCount = 0;

            for (long c : cable) {
                totalCutCount += c / midCM;
            }

            if (totalCutCount >= need) {
                bestLength = midCM;
                minCM = midCM + 1; // 더 길게 자름
            } else {
                maxCM = midCM - 1; // 더 짧게 자름
            }

        }


        System.out.println(bestLength);


    }

}
