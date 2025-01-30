package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17612 {

    static int currTime = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());

        Counters counters = new Counters(k);
        Queue<Customer> customers = new ArrayDeque<>();

        while (N-- > 0) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int id = Integer.parseInt(stringTokenizer.nextToken());
            int w = Integer.parseInt(stringTokenizer.nextToken());

            Customer customer = new Customer(id, w);
            customers.add(customer);
        }

        Queue<Customer> waitingCustomers = new ArrayDeque<>();

        while (!customers.isEmpty()) {

            for (int i = 0; i < counters.emptyCount; i++) {
                if (customers.isEmpty()) {
                    break;
                }
                Customer customer = customers.poll();
                waitingCustomers.add(customer);
            }
            counters.register(waitingCustomers);
            counters.process();
        }

        while (counters.emptyCount != k) {
            counters.process();
        }

        System.out.println(counters.getResult());
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
    }

    private static class Counter {
        int idx;
        Customer customer = null;

        public Counter(int idx) {
            this.idx = idx;
        }

        public boolean isEmpty() {
            return this.customer == null;
        }

        public void register(Customer customer) {
            this.customer = customer;
            customer.w += currTime;
            customer.counterIdx = idx;
        }

        public Customer process(int time) {
            if (this.customer.w == time) {
                Customer result = this.customer;
                result.finishTime = currTime;
                this.customer = null;
                return result;
            }
            return null;
        }
    }

    private static class Counters {
        List<Counter> counters = new ArrayList<>();
        PriorityQueue<Customer> processedCustomer = new PriorityQueue<>(
                (a, b) -> {
                    if (a.finishTime == b.finishTime) {
                        return b.counterIdx - a.counterIdx;
                    }
                    return a.finishTime - b.finishTime;
                }
        );

        int emptyCount;

        public Counters(int k) {
            for (int i = 0; i < k; i++) {
                this.counters.add(new Counter(i));
            }
            emptyCount = k;
        }

        public void register(Queue<Customer> customers) {
            for (Counter counter : counters) {
                if (counter.isEmpty() && !customers.isEmpty()) {
                    counter.register(customers.poll());
                    emptyCount--;
                }
            }
        }

        public void process() {
            int minTime = calcMinTime();

            for (Counter counter : counters) {
                if (counter.isEmpty()) {
                    continue;
                }
                Customer result = counter.process(minTime);
                if (result == null) {
                    continue;
                }
                processedCustomer.offer(result);
                emptyCount++;
            }

            currTime = minTime;
        }

        private int calcMinTime() {
            int min = Integer.MAX_VALUE;

            for (Counter counter : counters) {
                if (counter.isEmpty()) {
                    continue;
                }

                min = Math.min(min, counter.customer.w);
            }
            return min;
        }

        public long getResult() {
            int seq = 1;
            long result = 0;

            while (!processedCustomer.isEmpty()) {
                Customer customer = processedCustomer.poll();
                result += (long) customer.id * seq++;
            }
            return result;
        }
    }
}
