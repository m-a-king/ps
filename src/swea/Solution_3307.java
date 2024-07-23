package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3307 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        for (int t = 0; t < tc; t++) {
            int n = Integer.parseInt(bufferedReader.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            int[] LIS = new int[n + 1];
            int tailIdx = 0;
//            System.out.println();
            for (int i = 0; i < n; i++) {
//
//                for (int lis : LIS) {
//                    System.out.print(lis + " ");
//                }
//                System.out.println();

                int currNum = Integer.parseInt(stringTokenizer.nextToken());
                if (LIS[tailIdx] < currNum) LIS[++tailIdx] = currNum;
                else {
                    int start = 0;
                    int end = tailIdx;
                    int mid = 0;
                    while (start < end) {
                        mid = (start + end) / 2;

                        if (currNum > LIS[mid]) {
                            start = mid + 1;
                        } else {
                            end = mid;
                        }
                    }

                    LIS[end] = currNum;
                }
            }

            stringBuilder.append("#").append(t + 1).append(" ").append(tailIdx).append("\n");
        }
        System.out.println(stringBuilder);

    }
}
