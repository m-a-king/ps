package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ20040 {

    static int[] parents;


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int to = Integer.parseInt(stringTokenizer.nextToken());
            int from = Integer.parseInt(stringTokenizer.nextToken());

            int rootTo = find(to);
            int rootFrom = find(from);

            if (rootTo== rootFrom) {
                System.out.println(i);
                return;
            }

            union(rootTo, rootFrom);
        }

        System.out.println(0);

    }

    private static void union(int rootTo, int rootFrom) {

        if (rootTo < rootFrom) {
            parents[rootFrom] = rootTo;
        } else {
            parents[rootTo] = rootFrom;
        }

    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }

        return find(parents[x]);
    }
}
