package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ14003 {
    static int[] input, LIS, idxVisitor;
    static int trackingIdx = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        input = new int[n];
        LIS = new int[n];
        idxVisitor = new int[n];

        input[0] = LIS[0] = Integer.parseInt(stringTokenizer.nextToken());
        idxVisitor[0] = 0;

        for (int i = 1; i < n; i++) {
            int number = Integer.parseInt(stringTokenizer.nextToken());
            input[i] = number;

            if (LIS[trackingIdx] < number) {
                trackingIdx++;
                LIS[trackingIdx] = number;
                idxVisitor[i] = trackingIdx;
            } else {
                int correctIdx = binarySearch(number);
                LIS[correctIdx] = number;
                idxVisitor[i] = correctIdx;
            }
        }


//        for (int i : idxVisitor) {
//            System.out.print(i + " ");
//        }
//        System.out.println();

        Stack<Integer> stack = new Stack<>();

        int targetIdx = trackingIdx;
        for (int i = n - 1; i >= 0; i--) {
            if (idxVisitor[i] == targetIdx) {
                targetIdx--;
                stack.push(input[i]);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(trackingIdx + 1).append("\n");
        for (int i = 0; i <= trackingIdx; i++) {
            stringBuilder.append(stack.pop()).append(" ");
        }

        System.out.println(stringBuilder);

    }


    private static int binarySearch(int target) {
        int start = 0;
        int end = trackingIdx;
        int mid = 0;

        while (start < end) {
            mid = (start + end) / 2;

            if (LIS[mid] < target) {
                start = mid + 1;

            } else {
                end = mid;

            }
        }

        return end;
    }
}
