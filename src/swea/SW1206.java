package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

class SW1206 {
    public static void main(String args[]) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            int count = parseInt(bufferedReader.readLine());
            int[] building = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int subTotal = 0;

            for (int j = 2; j < count - 2; j++) {
                int currentBuilding = building[j];
                int leftTwo = building[j - 2];
                int leftOne = building[j - 1];
                int rightOne = building[j + 1];
                int rightTwo = building[j + 2];

                int min = Math.min(Math.min(currentBuilding - leftTwo, currentBuilding - leftOne),
                        Math.min(currentBuilding - rightOne, currentBuilding - rightTwo));

                if (min > 0) {
                    subTotal += min;
                }
            }

            System.out.println("#" + (i + 1) + " " + subTotal);

        }
    }
}