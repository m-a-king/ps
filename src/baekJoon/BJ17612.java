package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ17612 {

    static int SEQ = 1;
    static int TIME = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());


        while (N-- > 0) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int id = Integer.parseInt(stringTokenizer.nextToken());
            int w = Integer.parseInt(stringTokenizer.nextToken());

            Customer customer = new Customer(id, w);


        }
    }

    private static class Customer {
        int id;
        int w;

        public Customer(int id, int w) {
            this.id = id;
            this.w = w;
        }
    }

    private static class Counter {
        int idx;
        PriorityQueue<Customer> pq = new PriorityQueue<>();

        public Counter(int idx) {
            this.idx = idx;
        }

        public boolean isEmpty() {
            return pq.isEmpty();
        }

        public void register(Customer customer) {
            pq.add(customer);
        }

        public int process() {
            if (pq.isEmpty()) {
                return 0;
            }
            Customer customer = pq.poll();

            return customer.id * customer.w;
        }
    }

    private static class Counters {
        List<Counter> counters = new ArrayList<>();

        public Counters(int k) {
            for (int i = 0; i < k; i++) {
                this.counters.add(new Counter(i));
            }
        }

        public boolean haveEmptyCounter() {
            for(Counter counter: counters) {
                if (counter.isEmpty()) {
                    return true;
                }
            }
            return false;
        }

        public void register(Customer customer) {
            for(Counter counter: counters) {
                if (counter.isEmpty()) {
                    counter.register(customer);
                }
            }
        }
    }
}
