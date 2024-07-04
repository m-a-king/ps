package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1005 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(bufferedReader.readLine());

        for (int t = 0; t < tc; t++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int buildingCount = Integer.parseInt(stringTokenizer.nextToken());
            int ruleCount = Integer.parseInt(stringTokenizer.nextToken());

            int[] times = new int[buildingCount + 1];
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int i = 1; i <= buildingCount; i++) {
                times[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            List<List<Integer>> rules = new ArrayList<>();

            for (int i = 0; i <= buildingCount; i++) {
                rules.add(new ArrayList<>());
            }

            int[] needCount = new int[buildingCount + 1];

            for (int i = 0; i < ruleCount; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                int curr = Integer.parseInt(stringTokenizer.nextToken());
                int next = Integer.parseInt(stringTokenizer.nextToken());
                rules.get(curr).add(next); // curr이 지어져야만 next가 지어진다.
                needCount[next]++; // next가 지어지기까지 필요한 빌딩 개수
            }

            int targetBuilding = Integer.parseInt(bufferedReader.readLine());

            Queue<Integer> queue = new ArrayDeque<>();

            int[] buildTimes = new int[buildingCount + 1];

            for (int i = 1; i <= buildingCount; i++) {
                if (needCount[i] == 0) {
                    queue.offer(i);
                    buildTimes[i] = times[i];
                }
            }
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                if (curr == targetBuilding) {
                    break;
                }

                for (int next : rules.get(curr)) {
                    buildTimes[next] = Math.max(buildTimes[next], buildTimes[curr] + times[next]);

                    // next가 지어지기까지 필요한 빌딩 개수가 0이라면
                    if (--needCount[next] == 0) {
                        queue.offer(next);
                    }
                }

            }

            System.out.println(buildTimes[targetBuilding]);


        }
    }
}
