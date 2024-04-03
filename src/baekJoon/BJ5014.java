package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ5014 {

    static int totalFloor, upButton, downButton;
    static int[] dx = new int[2];


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        totalFloor = Integer.parseInt(stringTokenizer.nextToken());
        int startFloor = Integer.parseInt(stringTokenizer.nextToken());
        int targetFloor = Integer.parseInt(stringTokenizer.nextToken());
        upButton = Integer.parseInt(stringTokenizer.nextToken());
        downButton = Integer.parseInt(stringTokenizer.nextToken());
        dx[0] = upButton;
        dx[1] = -downButton;

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[totalFloor + 1];
        int[] countButton = new int[totalFloor + 1];

        queue.offer(startFloor);

        while (!queue.isEmpty()) {
            Integer currentFloor = queue.poll();

            if (currentFloor == targetFloor) {
                System.out.println(countButton[currentFloor]);
                return;
            }
            for (int i = 0; i < 2; i++) {
                int nextFloor = currentFloor + dx[i];
                if (isSafe(nextFloor) && !visited[nextFloor] && dx[i]!=0) {
                    visited[nextFloor] = true;
                    queue.offer(nextFloor);
                    countButton[nextFloor] = countButton[currentFloor] + 1;
                }

            }

        }

        System.out.println("use the stairs");

    }

    private static boolean isSafe(int nextFloor) {
        return 1 <= nextFloor && nextFloor <= totalFloor;
    }
}
