package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ8980 {

    static private class Delivery {
        int start, end, count;

        public Delivery(int start, int end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int townCount = Integer.parseInt(stringTokenizer.nextToken());
        int capacity = Integer.parseInt(stringTokenizer.nextToken());

        List<Delivery> deliveries = new ArrayList<>();

        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            int count = Integer.parseInt(stringTokenizer.nextToken());

            deliveries.add(new Delivery(start, end, count));
        }

        // 도착 마을을 기준으로 정렬
        // 도착마을이 같다면 시작 마을을 기준으로 정렬
        deliveries.sort((a, b) -> {
            if (a.end == b.end) return a.start - b.start;
            return a.end - b.end;
        });

        int[] truckCapacity = new int[townCount + 1]; // 마지막 도시는 필요없음
        Arrays.fill(truckCapacity, capacity);
        int answer = 0;

        for (int i = 0; i < n; i++) {
            // 현재 택배 정보
            Delivery curr = deliveries.get(i);

            // 현재 트럭이 해당 마을로 갔을 때, 해당 마을에서 담을 수 있는 택배의 개수 중 *최소값*
            // 즉, 현재 트럭이 해당 경로에서 담을 수 있는 최대 택배 개수
            int minCapacity = Integer.MAX_VALUE;

            // 도착 마을은 택배를 내리므로 확인하지 않음
            for (int s = curr.start; s < curr.end; s++) {
                minCapacity = Math.min(minCapacity, truckCapacity[s]);
            }

            // 트럭이 실을 수 있는 택배의 양이 현재 택배 수보다 적다면
            if (minCapacity < curr.count) {
                answer += minCapacity; // 실을 수 있는 만큼만 싣는다

                // 현재 택배 경로 동안
                for (int s = curr.start; s < curr.end; s++) {
                    truckCapacity[s] -= minCapacity; // 실은 개수만큼 실을 수 없음
                }
            }
            // 트럭이 모든 택배를 실을 수 있다면
            else {
                answer += curr.count; // 모두 싣는다

                // 현재 택배 경로 동안
                for (int s = curr.start; s < curr.end; s++) {
                    truckCapacity[s] -= curr.count; // 실은 개수만큼 실을 수 없음
                }
            }
        }

        System.out.println(answer);

    }
}
