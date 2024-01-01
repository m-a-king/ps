package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class SW1208 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        for (int n = 0; n < 10; n++) {
            int dumpCount = parseInt(bufferedReader.readLine());

            int[] boxes = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int x = 0; x < dumpCount; x++) {
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                int maxIndex = -1;
                int minIndex = -1;

                for (int i = 0; i < boxes.length; i++) {
                    if (boxes[i] > max) {
                        max = boxes[i];
                        maxIndex = i;
                    }
                    if (boxes[i] < min) {
                        min = boxes[i];
                        minIndex = i;
                    }
                }

                boxes[maxIndex]--;
                boxes[minIndex]++;
            }

            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            for (int box : boxes) {
                if (box > max) {
                    max = box;
                } else if (box < min) {
                    min = box;
                }
            }

            int diff = max - min;


            System.out.println("#" + (n+1) + " "+ diff);
        }


    }
}
