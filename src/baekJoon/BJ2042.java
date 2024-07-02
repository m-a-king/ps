package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2042 {

    static long[] numbers;
    static long[] segTree;
    static int[] leafNodeIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());

        numbers = new long[n + 1];
        segTree = new long[4 * n];
        leafNodeIdx = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            numbers[i] = Long.parseLong(bufferedReader.readLine());
        }

        initSegTree(1, 1, n);


        for (int i = 0; i < m + k; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            long a = Long.parseLong(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            long c = Long.parseLong(stringTokenizer.nextToken());

            if (a == 1) {
                changeNode(b, c);
                continue;
            }

            if (a == 2) {
                System.out.println(calcSum(b, (int) c, 1, n, 1));
                continue;
            }
        }

    }

    private static void changeNode(int b, long c) {
        int targetIdx = leafNodeIdx[b];
        long targetValue = segTree[targetIdx];

        long diff = targetValue - c;
        segTree[targetIdx] = c; // 리프 노드 변경
        for (targetIdx /= 2; targetIdx > 0; targetIdx /= 2) {
            segTree[targetIdx] -= diff; // 부모 노드 변경
        }
    }

    // start : 구간 합의 시작
    // end : 구간 합의 끝
    // left : 현재 세그먼트 트리 노드 범위의 시작
    // right : 현재 세그먼트 트리 노드 범위의 시작
    // nodeIdx : 현재 세그먼트 트리 노드 인덱스
    private static long calcSum(int start, int end, int left, int right, int nodeIdx) {
        // 현재 노드의 범위가 구간 합 범위에 완전히 속하지 않는다면
        if (left > end || right < start) {
            return 0;
        }

        // 현재 노드의 범위가 구간 합 범위에 완전히 속한다면
        if (left >= start && right <= end) {
            return segTree[nodeIdx];
        }

        int mid = (left + right) / 2;
        long leftSum = calcSum(start, end, left, mid, nodeIdx * 2);
        long rightSum = calcSum(start, end, mid + 1, right, nodeIdx * 2 + 1);

        return leftSum + rightSum;
    }


    private static void initSegTree(int nodeIdx, int start, int end) {
        if (start == end) {
            segTree[nodeIdx] = numbers[start];
            leafNodeIdx[start] = nodeIdx;
            return;
        }

        int midIdx = (start + end) / 2;

        initSegTree(nodeIdx * 2, start, midIdx); // leftChild
        initSegTree(nodeIdx * 2 + 1, midIdx+1, end); // rightChild
        segTree[nodeIdx] = segTree[nodeIdx * 2] + segTree[nodeIdx * 2 + 1]; // 좌우 자식 노드 값을 더하여 부모 노드 값 갱신

    }
}
