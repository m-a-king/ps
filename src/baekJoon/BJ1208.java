package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1208 {

    static int s = 0;
    static int n = 0;
    static long count = 0;
    static int[] number;
    static List<Integer> sum1 = new ArrayList<>();
    static List<Integer> sum2 = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        s = Integer.parseInt(stringTokenizer.nextToken());

        number = new int[n];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        for (int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        if (n == 1) {
            if (number[0] == s) {
                System.out.println(1);
                return;
            }
            System.out.println(0);
            return;
        }


        dfs(0, n / 2, 0, 0);
        dfs(n / 2, n, 0, 0);

        sum1.sort(Comparator.naturalOrder());
        sum2.sort(Comparator.naturalOrder());

//        System.out.println("sum1");
//        for (int sum : sum1) {
//            System.out.print(sum + " ");
//        }
//
//        System.out.println();
//        System.out.println("sum2");
//        for (int sum : sum2) {
//            System.out.print(sum + " ");
//        }
//        System.out.println();

        int p1 = 0;
        int p2 = sum2.size() - 1;

        while (p1 < sum1.size() && p2 >= 0) {
            int total = sum1.get(p1) + sum2.get(p2);

            if (total > s) {
                p2--;
            } else if (total < s) {
                p1++;
            } else {
                long temp1 = 1;
                long temp2 = 1;

                while (p1 + 1 < sum1.size() && sum1.get(p1).equals(sum1.get(p1 + 1))) {
                    temp1++;
                    p1++;
                }

                while (p2 - 1 >= 0 && sum2.get(p2).equals(sum2.get(p2 - 1))) {
                    temp2++;
                    p2--;
                }

                count += temp1 * temp2;
                p1++;
                p2--;
            }
        }


        if (s == 0) {
            count--;
        }
        System.out.println(count);
    }

    private static void dfs(int start, int end, int depth, int sum) {

        if (depth == end - start) {
            if (start == 0) sum1.add(sum);
            else sum2.add(sum);
            return;
        }

        dfs(start, end, depth + 1, sum + number[start + depth]);
        dfs(start, end, depth + 1, sum);
    }
}
