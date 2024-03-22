package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ22968 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int[] maxHeight = new int[44];
        maxHeight[1] = 1;
        maxHeight[2] = 2;
        maxHeight[3] = 4;
        maxHeight[4] = 7;
        // maxHeight[5] = 7 + 4 + 1;

        for (int i = 5; i < maxHeight.length; i++) {
            maxHeight[i] = maxHeight[i - 2] + maxHeight[i - 1] + 1;
        }

        int t = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int v = Integer.parseInt(bufferedReader.readLine());

            // 이진 탐색
            int left = 1;
            int right = maxHeight.length - 1; // 43

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (maxHeight[mid] <= v) {
                    if (maxHeight[mid +1] > v) {
                        stringBuilder.append(mid).append("\n");
                        break;
                    } else {
                        left = mid + 1;
                    }

                } else {
                    right = mid - 1;

                }


            }
        }

        System.out.println(stringBuilder);
    }
}
