package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ31809_pq {

    private static class Computer {
        int idx;
        int day;

        public Computer(int idx, int day) {
            this.idx = idx;
            this.day = day;
        }
    }

    static int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(bufferedReader.readLine());
        StringBuilder results = new StringBuilder();

        while (tc-- > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int N = Integer.parseInt(stringTokenizer.nextToken());
            int M = Integer.parseInt(stringTokenizer.nextToken());
            int P = Integer.parseInt(stringTokenizer.nextToken());
            int K = Integer.parseInt(stringTokenizer.nextToken());

            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int[] C = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                C[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            int[] indegree = new int[N + 1];

            while (M-- > 0) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int X = Integer.parseInt(stringTokenizer.nextToken());
                int Y = Integer.parseInt(stringTokenizer.nextToken());

                graph.get(X).add(Y);
                indegree[Y]++;
            }

            PriorityQueue<Computer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.day));
            for (int i = 1; i <= N; i++) {
                if (indegree[i] == 0) {
                    pq.offer(new Computer(i, C[i]));
                }
            }

            int currMalware = N;
            long result = 0;
            int currDay = 0;
            boolean[] finished = new boolean[N + 1];

            while (++currDay <= K) {
                if (pq.isEmpty()) {
                    result = (result + ((long) currMalware * (K - currDay + 1)) % MOD) % MOD;
                    break;
                }

                Computer currComputer = pq.peek();

                if (currComputer.day > K) {
                    result = (result + ((long) currMalware * (K - currDay + 1)) % MOD) % MOD;
                    break;
                }

                if (currComputer.day > currDay) {
                    result = (result + ((long) currMalware * (currComputer.day - currDay)) % MOD) % MOD;
                    currDay = currComputer.day;
                }

                while (!pq.isEmpty()) {
                    if (pq.peek().day != currDay) {
                        break;
                    }

                    currComputer = pq.poll();

                    if (finished[currComputer.idx]) {
                        continue;
                    }
                    finished[currComputer.idx] = true;
                    currMalware--;

                    for (int adj : graph.get(currComputer.idx)) {
                        indegree[adj]--;
                        if (indegree[adj] == 0 && !finished[adj]) {
                            int nextScheduledDay = calcNextScheduledDay(adj, currDay, C, currComputer, P);
                            pq.offer(new Computer(adj, nextScheduledDay));
                        }
                    }
                }

                result = (result + currMalware) % MOD;
            }

            results.append(result).append("\n");
        }

        System.out.println(results.toString().trim());
    }

    private static int calcNextScheduledDay(int adj, int currDay, int[] C, Computer currComputer, int P) {
        int nextScheduledDay = currDay + C[adj] - C[currComputer.idx];
        if (C[currComputer.idx] > C[adj]) {
            nextScheduledDay += P;
        }
        return nextScheduledDay;
    }
}
