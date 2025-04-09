package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ33070 {
    static int N, K;
    static boolean[] possible;
    static int[] base;
    static int[] init;

    static SegTree seg;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        possible = new boolean[N + 10]; // 여유 있게
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (a < possible.length) {
                possible[a] = true;
            }
        }

        init = new int[N + 1];
        base = new int[N + 1];
        int cntOff = 0;
        int onCount = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int state = Integer.parseInt(st.nextToken());
            init[i] = state;
            if (state == 0) {
                cntOff++;
                base[i] = -1;
            } else {
                base[i] = cntOff;
                onCount++;
            }
        }

        seg = new SegTree(N);
        seg.build(1, 1, N);

        int additionalOff = 0; // 추가로 소등한 on 방 수

        while (true) {
            int idx = seg.query(1, 1, N);
            if (idx == -1) break;
            seg.updatePoint(1, 1, N, idx); // idx번 방 소등 처리
            additionalOff++;
            if (idx < N)
                seg.updateRange(1, 1, N, idx + 1, N, 1);
        }

        int answer = onCount - additionalOff;
        System.out.println(answer);
    }

    static class SegTree {
        int n;
        int[] tree;
        int[] lazy;
        int[] eff;

        public SegTree(int n) {
            this.n = n;
            tree = new int[4 * n];
            lazy = new int[4 * n];
            eff = new int[n + 1]; // 1-indexed
        }

        int calcLeaf(int i) {
            if (init[i] == 0) return -1;
            return (possible[eff[i]] ? i : -1);
        }

        void pushUp(int node) {
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        void pushDown(int node, int l, int r) {
            if (lazy[node] != 0) {
                int mid = (l + r) >> 1;
                updateNode(node * 2, l, mid, lazy[node]);
                updateNode(node * 2 + 1, mid + 1, r, lazy[node]);
                lazy[node] = 0;
            }
        }

        void updateNode(int node, int l, int r, int delta) {
            lazy[node] += delta;
            if (l == r) {
                eff[l] += delta;
                tree[node] = calcLeaf(l);
            }
        }

        void updateRange(int node, int l, int r, int ql, int qr, int delta) {
            if (ql <= l && r <= qr) {
                updateNode(node, l, r, delta);
                return;
            }
            pushDown(node, l, r);
            int mid = (l + r) >> 1;
            if (ql <= mid) updateRange(node * 2, l, mid, ql, qr, delta);
            if (qr > mid) updateRange(node * 2 + 1, mid + 1, r, ql, qr, delta);
            pushUp(node);
        }

        void updatePoint(int node, int l, int r, int pos) {
            if (l == r) {
                init[pos] = 0;
                tree[node] = -1;
                return;
            }
            pushDown(node, l, r);
            int mid = (l + r) >> 1;
            if (pos <= mid) updatePoint(node * 2, l, mid, pos);
            else updatePoint(node * 2 + 1, mid + 1, r, pos);
            pushUp(node);
        }

        void build(int node, int l, int r) {
            if (l == r) {
                if (init[l] == 1)
                    eff[l] = base[l];
                else
                    eff[l] = -1000000000;
                tree[node] = calcLeaf(l);
                return;
            }
            int mid = (l + r) >> 1;
            build(node * 2, l, mid);
            build(node * 2 + 1, mid + 1, r);
            pushUp(node);
        }

        int query(int node, int l, int r) {
            if (l == r)
                return calcLeaf(l);
            pushDown(node, l, r);
            int mid = (l + r) >> 1;
            int rightCandidate = query(node * 2 + 1, mid + 1, r);
            if (rightCandidate != -1)
                return rightCandidate;
            return query(node * 2, l, mid);
        }
    }
}
