package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BJ33070 {

    private static class SegmentTree {
        int[] tree;
        int n;
        int value;

        public SegmentTree(int[] origin, int value) {
            this.n = origin.length - 1;
            this.value = value;
            this.tree = new int[4 * n];
            build(origin, 1, 1, n);
        }

        public int query(int right) {
            return query(1, 1, n, 1, right);
        }

        public void add(int target) {
            update(1, 1, n, target);
        }

        private void build(int[] origin, int node, int start, int end) {
            if (isLeaf(start, end)) {
                tree[node] = origin[start];
                return;
            }

            int mid = calcMid(start, end);
            build(origin, node * 2, start, mid);
            build(origin, node * 2 + 1, mid + 1, end);

            calcParent(node);
        }

        private int query(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 0;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = calcMid(start, end);
            int leftSum = query(node * 2, start, mid, left, right);
            int rightSum = query(node * 2 + 1, mid + 1, end, left, right);
            return leftSum + rightSum;
        }

        private void update(int node, int start, int end, int target) {
            if (isLeaf(start, end)) {
                tree[node] += value;
                return;
            }

            int mid = calcMid(start, end);
            if (target <= mid) {
                update(node * 2, start, mid, target);
            } else {
                update(node * 2 + 1, mid + 1, end, target);
            }

            calcParent(node);
        }

        private static int calcMid(int start, int end) {
            return (start + end) / 2;
        }


        private static boolean isLeaf(int start, int end) {
            return start == end;
        }

        private void calcParent(int node) {
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        int[] A = new int[K];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < K; i++) {
            A[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(A);
        HashSet<Integer> searchableA = Arrays.stream(A)
                .boxed()
                .collect(Collectors.toCollection(HashSet::new));


        int[] light = new int[N + 1];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= N; i++) {
            light[i] = stringTokenizer.nextToken().equals("1") ? 0 : 1;
        }

        SegmentTree segmentTree = new SegmentTree(light, 1);
        int totalOff = segmentTree.query(N);

        boolean updated = true;
        while (updated) {
            updated = false;
            for (int i = N; i > 0; i--) {
                if (light[i] == 1) continue;

                int offCount = segmentTree.query(i - 1);
                if (searchableA.contains(offCount)) {
                    light[i] = 1;
                    segmentTree.add(i);
                    totalOff++;
                    updated = true;
                    break;
                }
            }
        }

        System.out.println(N - totalOff);
    }
}
