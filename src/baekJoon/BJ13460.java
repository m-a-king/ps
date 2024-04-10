package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ13460 {

    private static class BeadsState {
        int redRow, redCol, blueRow, blueCol, count;

        public BeadsState(int redRow, int redCol, int blueRow, int blueCol, int count) {
            this.redRow = redRow;
            this.redCol = redCol;
            this.blueRow = blueRow;
            this.blueCol = blueCol;
            this.count = count;
        }
    }

    static char[][] map;
    static int maxRow, maxCol;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        maxRow = Integer.parseInt(stringTokenizer.nextToken());
        maxCol = Integer.parseInt(stringTokenizer.nextToken());

        map = new char[maxRow][maxCol];
        BeadsState beadsState = new BeadsState(0, 0, 0, 0, 0);

        for (int i = 0; i < maxRow; i++) {
            String input = bufferedReader.readLine();
            for (int j = 0; j < maxCol; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'B') {
                    beadsState.blueRow = i;
                    beadsState.blueCol = j;
                } else if (map[i][j] == 'R') {
                    beadsState.redRow = i;
                    beadsState.redCol = j;
                }
            }
        }

        Queue<BeadsState> queue = new ArrayDeque<>();
        queue.offer(beadsState);

        while (!queue.isEmpty()) {
            BeadsState current = queue.poll();

            if (current.count == 10) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int redRow = current.redRow;
                int redCol = current.redCol;
                int blueRow = current.blueRow;
                int blueCol = current.blueCol;
                boolean isGoalRed = false;
                boolean isGoalBlue = false;

                while (true) {
                    int nextRedRow = redRow + dx[d];
                    int nextRedCol = redCol + dy[d];
                    if (map[nextRedRow][nextRedCol] == '#') {
                        break;
                    }

                    if (map[nextRedRow][nextRedCol] == 'O') {
                        isGoalRed = true;
                        break;
                    }

                    redRow = nextRedRow;
                    redCol = nextRedCol;
                }

                while (true) {
                    int nextBlueRow = blueRow + dx[d];
                    int nextBlueCol = blueCol + dy[d];
                    if (map[nextBlueRow][nextBlueCol] == '#') {
                        break;
                    }

                    if (map[nextBlueRow][nextBlueCol] == 'O') {
                        isGoalBlue = true;
                        break;
                    }

                    blueRow = nextBlueRow;
                    blueCol = nextBlueCol;
                }

                if (isGoalBlue) {
                    continue;
                } else if (isGoalRed) {
                    System.out.println(current.count + 1);
                    return;
                }

                if (redRow == blueRow && redCol == blueCol) {
                    if (d == 1) { // 구슬이 아랫쪽으로 움직인 경우
                        if (current.redRow < current.blueRow) redRow--;
                        else blueRow--;
                    } else if (d == 0) { // 구슬이 윗쪽으로 움직인 경우
                        if (current.redRow > current.blueRow) redRow++;
                        else blueRow++;
                    } else if (d == 3) { // 구슬이 왼쪽으로 움직인 경우
                        if (current.redCol < current.blueCol) redCol--;
                        else blueCol--;
                    } else if (d == 2) { // 구슬이 오른쪽으로 움직인 경우
                        if (current.redCol > current.blueCol) redCol++;
                        else blueCol++;
                    }
                }
                queue.offer(new BeadsState(redRow, redCol, blueRow, blueCol, current.count + 1));
            }
        }

        System.out.println(-1); // 실패 또는 10회 초과 시
    }
}
