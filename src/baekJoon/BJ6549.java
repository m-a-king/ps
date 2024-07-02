package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ6549 {

    static long[] heights;
    static int[] segmentTree;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            int n = Integer.parseInt(stringTokenizer.nextToken());
            if (n == 0) {
                return; // 종료
            }

            heights = new long[n];

            for (int i = 0; i < n; i++) {
                heights[i] = Long.parseLong(stringTokenizer.nextToken());
            }

            // 세그먼트 트리 초기화
            int h = (int) Math.ceil(Math.log(n) / Math.log(2));  // 트리의 높이 계산
            int size = (int) Math.pow(2, h + 1);  // 전체 노드의 수를 구하여 배열 크기 결정, 루트 노드 인덱스 -> 1
            segmentTree = new int[size];  // 세그먼트 트리 배열 초기화 (4 * n)

            // 범위 -> 0 ~ (n-1)
            // 노드 인덱스 -> 1 (루트)
            initSegmentTree(0, n - 1,1);

            System.out.println(getMaxArea(0, n - 1));
        }

    }

    // 세그먼트 트리 초기화
    private static void initSegmentTree(int start, int end, int nodeIdx) {
        // 더 이상 분할할 수 없는 경우 (리프 노드)
        if (start == end) {
            segmentTree[nodeIdx] = start;  // 리프 노드에는 해당 위치의 인덱스를 저장
            return;
        }

        // 중간 인덱스 계산
        int midIdx = (start + end) / 2;
        // 왼쪽 자식 노드 인덱스
        int leftIdx = 2 * nodeIdx;
        // 오른쪽 자식 노드 인덱스
        int rightIdx = 2 * nodeIdx + 1;

        // 왼쪽 자식 노드 초기화 (start ~ mid)
        initSegmentTree(start, midIdx, leftIdx);

        // 오른쪽 자식 노드 초기화 ((mid + 1) ~ end)
        initSegmentTree(midIdx + 1, end, rightIdx);

        // 왼쪽 자식 노드가 나타내는 범위의 최소 높이 인덱스
        int leftChild = segmentTree[leftIdx];

        // 오른쪽 자식 노드가 나타내는 범위의 최소 높이 인덱스
        int rightChild = segmentTree[rightIdx];

        // 부모 노드는 두 자식 노드 중 더 낮은 높이의 인덱스를 저장
        segmentTree[nodeIdx] = (heights[leftChild] < heights[rightChild]) ? leftChild : rightChild;
    }

    // 재귀적으로 최대 직사각형 넓이 계산
    private static long getMaxArea(int start, int end) {
        // 범위 없음
        if (start > end) {
            return 0;
        }

        // 한 칸
        if (start == end) {
            return heights[start];
        }

        // 전체 범위에서
        // 1번 노드(루트)부터
        // start ~ end 안의
        // 최소 높이 인덱스
        int minHeightIdx = findMinHeightIdx(0, heights.length - 1, 1, start, end);

        // 최소 높이 * 범위
        long maxArea = heights[minHeightIdx] * (end - start + 1);

        // 위 범위의 좌측 최대 넓이
        long leftArea = getMaxArea(start, minHeightIdx - 1);

        // 위 범위의 우측 최대 넓이
        long rightArea = getMaxArea(minHeightIdx + 1, end);

        // 세 범위 중 최대 넓이
        return Math.max(maxArea, Math.max(leftArea, rightArea));
    }

    // 세그먼트 트리에서 현재 노드를 기준으로 주어진 범위 내에서 최소 높이 인덱스 찾기
    // nodeIdx -> 현재 노드 인덱스
    // start ~ end -> 현재 노드가 바라보는 범위 (노드의 범위)
    // left ~ right -> 현재 최소 높이 인덱스를 찾는 범위
    private static int findMinHeightIdx(int start, int end, int nodeIdx, int left, int right) {
        // 현재 노드의 범위와 찾고 있는 범위가 겹치지 않는 경우
        if (left > end || right < start) {
            return -1;
        }

        // 현재 노드의 범위가 찾고 있는 범위에 완전히 포함되는 경우
        if (left <= start && end <= right) {
            return segmentTree[nodeIdx];
        }

        // 현재 노드의 범위가 찾고 있는 범위와 부분적으로 겹치는 경우
        // 세그먼트 트리의 두 자식 노드 중 찾고 있는 범위에 완전히 포함되는 경우를 찾기 위해 재귀 호출
        int mid = (start + end) / 2;
        int leftNode = findMinHeightIdx(start, mid, 2 * nodeIdx, left, right);
        int rightNode = findMinHeightIdx(mid + 1, end, 2 * nodeIdx + 1, left, right);

        // 두 자식 노드 중,
        // 하나가 찾고 있는 범위와 겹치지 않았다면,
        // 하나는 그 범위가 겹친다.
        if (leftNode == -1) {
            return rightNode;
        }

        if (rightNode == -1) {
            return leftNode;
        }

        // 두 자식 노드 모두 찾고 있는 범위와 겹쳤다면,
        // 더 낮은 높이를 갖는 노드의 인덱스를 반환한다.
        return (heights[leftNode] < heights[rightNode]) ? leftNode : rightNode;
    }


}
