package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Test1 {

    private static int[] findLayer(int target) {
        int layer = 0;
        int position = 0;
        int accel = 1;
        while (true) {
            position += accel++;
            layer++;
            if (position >= target) {
                int detail = layer - (position - target);
                return new int[]{layer, detail};
            }
        }
    }

    private static int move(int[] start, int[] end) {
        int currentLayer = start[0];
        int currentDetail = start[1];
        int endLayer = end[0];
        int endDetail = end[1];
        int moveCount = 0;

        while (currentLayer != endLayer) {
            currentLayer++;
            if (currentDetail < endDetail) {
                currentDetail++;
            }
            moveCount++;
        }

        while (currentDetail != endDetail) {
            if (currentDetail < endDetail) {
                currentDetail++;
            } else {
                currentDetail--;
            }
            moveCount++;
        }


        return moveCount;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < n; i++) {

            int[] startEnd = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

            if (startEnd[0] == startEnd[1]) {
                System.out.println("#" + (i + 1) + " " + "0");
            } else {

                int[] start = findLayer(startEnd[0]);

                int[] end = findLayer(startEnd[1]);

                int res = move(start, end);
                System.out.println("#" + (i + 1) + " " + res);

            }
        }
    }
}
