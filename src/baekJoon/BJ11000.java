package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ11000 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        int[][] lectures = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            lectures[i][0] = Integer.parseInt(stringTokenizer.nextToken());
            lectures[i][1] = Integer.parseInt(stringTokenizer.nextToken());
        }

        Arrays.sort(lectures, (room1, room2)->{
            if (room1[0] == room2[0]) {
                return room1[1] - room2[1];
            }
            return room1[0] - room2[0];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(lectures[0][1]); // 초기값 삽입 -> 가장 빠른 시작 시간 중 가장 빠른 종료 시간

        for (int i = 1; i < lectures.length; i++) {
            int currEnd = pq.peek();
            int nextStart = lectures[i][0];
            int nextEnd = lectures[i][1];

            // 현재 큐에서 가장 빨리 끝나는 시간 = currEnd
            // 다음으로 큐에 들어가야할 강의의 시작시간 = nextStart
            if (currEnd <= nextStart) {
                pq.poll();
            }
            pq.offer(nextEnd);
        }

        System.out.println(pq.size());



    }
}
