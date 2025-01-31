package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ17612_optimized {

    private static class Counter {
        int idx;
        int finishTime;

        public Counter(int idx, int finishTime) {
            this.idx = idx;
            this.finishTime = finishTime;
        }
    }

    private static class Customer {
        int id;
        int w;
        int counterIdx;
        int finishTime;

        public Customer(int id, int w) {
            this.id = id;
            this.w = w;
        }

        public void fixCounterInfo(Counter counter) {
            this.counterIdx = counter.idx;
            this.finishTime = counter.finishTime;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());

        PriorityQueue<Counter> counters = new PriorityQueue<>(
                (a, b) -> {
                    if (a.finishTime == b.finishTime) {
                        return a.idx - b.idx;
                    }
                    return a.finishTime - b.finishTime;
                }
        );

        List<Customer> processedCustomers = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            counters.add(new Counter(i, 0));
        }

        while (N-- > 0) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int id = Integer.parseInt(stringTokenizer.nextToken());
            int w = Integer.parseInt(stringTokenizer.nextToken());

            Customer customer = new Customer(id, w);

            Counter counter = counters.poll();
            assert counter != null;
            counter.finishTime += w;
            counters.add(counter);

            customer.fixCounterInfo(counter);
            processedCustomers.add(customer);
        }

        long result = 0;
        int seq = 1;

        processedCustomers.sort((a, b) -> {
            if (a.finishTime == b.finishTime) {
                return b.counterIdx - a.counterIdx;
            }
            return a.finishTime - b.finishTime;
        });

        for (Customer customer : processedCustomers) {
            result += (long) customer.id * seq++;
        }

        System.out.println(result);
    }
}
