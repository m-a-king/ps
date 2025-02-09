package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ31809 {

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
            int[] C = new int[N+1];
            for (int i = 1; i <= N; i++) {
                C[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            // P(주기)의 관점에서 바라본 박멸 순서
            int[] schedule = new int[P + 1];
            for (int i = 1; i <= N; i++) {
                schedule[C[i]] = i;
            }
            schedule[0] = schedule[P];

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

            // 최소 진입 간선 확인
            int minIndegree = Integer.MAX_VALUE;
            for (int curr : indegree) {
                minIndegree = Math.min(minIndegree, curr);
            }
            if (minIndegree > 0) {
                results.append(((long) N * K) % MOD).append("\n");
                continue;
            }

            int currMalware = N;
            long result = 0;
            int cycleDetector = 0;

            boolean[] finished = new boolean[N + 1];
            int day = 0;
            while (++day <= K) {
                int targetComputer = schedule[day % P];

                if (!finished[targetComputer] && targetComputer != 0 && indegree[targetComputer] == 0) {
                    finished[targetComputer] = true;
                    currMalware--;
                    cycleDetector = 0;

                    for (int to : graph.get(targetComputer)) {
                        indegree[to]--;
                    }
                } else {
                    cycleDetector++;
                }

                // P(주기)동안 멀웨어가 줄어들지 않았다면 단번에 계산 가능
                if (cycleDetector == P) {
                    result = result + ((long) currMalware * (K - day + 1) % MOD) % MOD;
                    break;
                }

                result = (result + currMalware % MOD) % MOD;
            }

            results.append(result % MOD).append("\n");
        }

        System.out.println(results.toString().trim());
    }
}
