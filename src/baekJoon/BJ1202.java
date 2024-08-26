package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1202 {

    private static class Jewelry implements Comparable<Jewelry> {
        int weight, value;

        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewelry o) {
            return o.value - this.value;
        }
    }

    static int n, k;
    static Jewelry[] jewelries;
    static int[] capacities;
    static boolean[] used;
    static PriorityQueue<Jewelry> pq = new PriorityQueue<>();


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());

        jewelries = new Jewelry[n];
        capacities = new int[k];

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());

            jewelries[i] = new Jewelry(m, v);
        }

        for (int i = 0; i < k; i++) {
            int c = Integer.parseInt(bufferedReader.readLine());

            capacities[i] = c;
        }

        Arrays.sort(jewelries, (a, b) -> a.weight - b.weight); // 오름차
        Arrays.sort(capacities); // 오름차순

        long res = 0;

        int j = 0;
        for (int i = 0; i < k; i++) {
            while (j < n && capacities[i] >= jewelries[j].weight) {
                pq.offer(jewelries[j++]);
            }

            if (!pq.isEmpty()) {
                res += pq.poll().value;
            }
        }

        System.out.println(res);

    }
}