package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class BJ1931 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = parseInt(bufferedReader.readLine());

        int[][] seTimes = new int[n][2];
        StringTokenizer stringTokenizer;

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            seTimes[i][0] = parseInt(stringTokenizer.nextToken());
            seTimes[i][1] = parseInt(stringTokenizer.nextToken());
        }

        Arrays.sort(seTimes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }

                return o1[1] - o2[1];
            }
        });

        int count = 0;
        int preEnd = 0;

        for (int[] seTime : seTimes) {
            if (preEnd <= seTime[0]) {
                preEnd = seTime[1];
                count++;
            }
        }

        System.out.println(count);
    }
}
