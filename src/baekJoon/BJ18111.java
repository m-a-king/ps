package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ18111 {

    static int block;
    static int totalHeight;
    static int averageHeight;
    static int time;
    static int[][] ground;
    static int minHeight = Integer.MAX_VALUE;
    static int maxHeight = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int[] fistLine = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int row = fistLine[0];
        int col = fistLine[1];
        block = fistLine[2];
        totalHeight = 0;
        time = 0;

        ground = new int[row][col];

        for (int i = 0; i < row; i++) {
            int[] line = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < col; j++) {
                ground[i][j] = line[j];
                totalHeight += line[j];
                minHeight = Math.min(minHeight, ground[i][j]);
                maxHeight = Math.max(maxHeight, ground[i][j]);
            }
        }

        int bestTime = Integer.MAX_VALUE;
        int bestHeight = -1;

        for (int targetHeight = minHeight; targetHeight <= maxHeight; targetHeight++) {
            int timeReq = 0;
            int blockReq = 0;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int diff = ground[i][j] - targetHeight;

                    if (diff > 0) {
                        timeReq += 2 * diff;
                        blockReq -= diff;
                    } else if (diff < 0) {
                        timeReq += diff * -1;
                        blockReq += diff * -1;
                    }
                }
            }

            if (blockReq <= block && timeReq <= bestTime) {
                bestTime = timeReq;
                bestHeight = targetHeight;
            }
        }

        System.out.println(bestTime + " " + bestHeight);


    }

}


