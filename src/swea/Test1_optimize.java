package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test1_optimize {

    private static int[] findLayer(int target) {
        int layer = (int) Math.ceil((-1 + Math.sqrt(1 + 8 * target)) / 2);
        int detail = target - layer * (layer - 1) / 2;

        return new int[]{layer, detail};
    }

    private static int move(int[] start, int[] end) {
        int layerDiff = end[0] - start[0];
        int detailDiff = end[1] - start[1];

        if (detailDiff >= 0) {
            if (detailDiff - layerDiff >= 0) {
                return layerDiff + (detailDiff - layerDiff);
            }
            return layerDiff;
        } else {
            return layerDiff - detailDiff;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < n; i++) {
            String[] parts = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);

            int start = Math.min(a, b);
            int end = Math.max(a, b);

            if (start == end) {
                System.out.println("#" + (i + 1) + " " + "0");
                continue;
            }

            int[] startLocation = findLayer(start);
            int[] endLocation = findLayer(end);
            int res = move(startLocation, endLocation);

            System.out.println("#" + (i + 1) + " " + res);
        }
    }
}
