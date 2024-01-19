package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Integer.parseInt;

public class BJ16928 {

    private static class Position {
        int location, diceCount;

        public Position(int location, int diceCount) {
            this.location = location;
            this.diceCount = diceCount;
        }
    }

    static int[] board = new int[101];
    static boolean[] visited = new boolean[101];

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");
        int ladderCount = parseInt(input[0]);
        int snakeCount = parseInt(input[1]);

        for (int i = 1; i <= ladderCount+snakeCount; i++) {
            String[] ladderSE = bufferedReader.readLine().split(" ");
            int start = parseInt(ladderSE[0]);
            int end = parseInt(ladderSE[1]);
            board[start] = end;

        }

        Queue<Position> queue = new ArrayDeque<>();
        visited[1] = true;
        queue.offer(new Position(1, 0));

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            if (current.location == 100) {
                System.out.println(current.diceCount);
                break;
            }

            for (int i = 1; i <= 6; i++) {
                int nextLocation = current.location + i;

                if (nextLocation <= 100 && !visited[nextLocation]) {
                    if (board[nextLocation] != 0) {
                        nextLocation = board[nextLocation];
                    }

                    if (!visited[nextLocation]) { // 사다리나 뱀에 의해 이동한 후의 위치도 방문 여부 확인 (위의 체크는 이동 전 체크...)
                        visited[nextLocation] = true;
                        queue.offer(new Position(nextLocation, current.diceCount + 1));
                    }
                }
            }
        }
    }

}
