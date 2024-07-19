package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BJ1744 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        // 양수   -> 5, 4, 3, 2, 1, 1, 1
        PriorityQueue<Integer> pNumPQ = new PriorityQueue<>(Comparator.reverseOrder());
        // 음수,0 -> -5, -4, -3, -2, -1, 0, 0
        PriorityQueue<Integer> nNumPQ = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            if (num > 0) pNumPQ.offer(num);
            else nNumPQ.offer(num);
        }

        int res = 0;
        while (pNumPQ.size() >= 2) {
            int num1 = pNumPQ.poll();
            int num2 = pNumPQ.poll();

            if (num1 == 1 || num2 == 1) {
                res += num1 + num2; // 1과 다른 수를 묶을 경우 더하는 것이 최대
            } else {
                res += num1 * num2;
            }
        }
        while (nNumPQ.size() >= 2) {
            int num1 = nNumPQ.poll();
            int num2 = nNumPQ.poll();

            res += num1 * num2;
        }

        res += pNumPQ.isEmpty() ? 0 : pNumPQ.poll();
        res += nNumPQ.isEmpty() ? 0 : nNumPQ.poll();
        System.out.println(res);


    }
}
