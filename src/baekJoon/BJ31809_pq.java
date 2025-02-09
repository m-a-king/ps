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

            // 진입 간선은 PQ의 조건에 포함되지 않는다: 진입 간선이 존재한다면 PQ에 포함될 수 없다
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
                // 처리할 수 있는 컴퓨터가 없다면 계산 후 종료
                if (pq.isEmpty()) {
                    result = (result + fastForward(K - currDay + 1, currMalware)) % MOD;
                    break;
                }

                Computer currComputer = pq.poll();
                if (finished[currComputer.idx]) {
                    continue;
                }
                finished[currComputer.idx] = true;
                // 기한 내에 처리할 수 있는 컴퓨터가 없다면 계산 후 종료
                if (currComputer.day > K) {
                    result = (result + fastForward(K - currDay + 1, currMalware)) % MOD;
                    break;
                }

                // 현재 날짜에 처리할 수 있는 컴퓨터가 없다면 처리할 수 있는 날까지 건너뛰기
                if (currComputer.day > currDay) {
                    result = (result + fastForward(currComputer.day - currDay, currMalware)) % MOD;
                    currDay = currComputer.day;
                }

                currMalware--;

                // 자신이 박멸되므로 박멸될 수 있는 요소를 큐에 추가
                for (int adj : graph.get(currComputer.idx)) {
                    indegree[adj]--;
                    if (indegree[adj] == 0 && !finished[adj]) {
                        int nextScheduledDay = calcNextScheduledDay(adj, currDay, C, currComputer, P);
                        pq.offer(new Computer(adj, nextScheduledDay));
                    }
                }

                result = (result + currMalware) % MOD;
            }

            results.append(result).append("\n");
        }

        System.out.println(results.toString().trim());
    }

    // 최적화: fast-forward를 수행하여 i일 동안 변화가 없는 박멸 개수를 한 번에 계산
    private static long fastForward(int i, int currMalware) {
        return (((long) currMalware * i) % MOD) % MOD;
    }

    // C를 고려해서 다음으로 박멸될 컴퓨터의 day를 계산
    private static int calcNextScheduledDay(int adj, int currDay, int[] C, Computer currComputer, int P) {
        int nextScheduledDay = currDay + C[adj] - C[currComputer.idx];
        if (C[currComputer.idx] > C[adj]) {
            nextScheduledDay += P;
        }
        return nextScheduledDay;
    }
}
