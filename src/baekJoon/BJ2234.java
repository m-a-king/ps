package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2234 {

    private static class WallChecker {
        boolean top, down, left, right;

        public WallChecker(boolean top, boolean down, boolean left, boolean right) {
            this.top = top;
            this.down = down;
            this.left = left;
            this.right = right;
        }
    }

    private static class State {
        int x, y;

        public State(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {0, 0, -1, 1}; // 좌, 우, 상, 하
    static int[] dy = {-1, 1, 0, 0}; // 좌, 우, 상, 하
    static int col;
    static int row;
    static WallChecker[][] wallCheckers;
    static int[][] roomNumbers;
    static List<Integer> roomSizes = new ArrayList<>();
    static int roomCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        col = Integer.parseInt(stringTokenizer.nextToken());
        row = Integer.parseInt(stringTokenizer.nextToken());
        wallCheckers = new WallChecker[row][col];
        roomNumbers = new int[row][col];

        for (int i = 0; i < row; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 0; j < col; j++) {
                int wallInfo = Integer.parseInt(stringTokenizer.nextToken());
                boolean left = (wallInfo & 1) != 0;
                boolean top = (wallInfo & 2) != 0;
                boolean right = (wallInfo & 4) != 0;
                boolean down = (wallInfo & 8) != 0;
                wallCheckers[i][j] = new WallChecker(top, down, left, right);
            }
        }

        int maxCount = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (roomNumbers[i][j] != 0) continue;

                roomCount++;
                int count = bfs(i, j, roomCount);
                roomSizes.add(count);

                if (count > maxCount) {
                    maxCount = count;
                }
            }
        }

        int maxCombineSize = maxCount;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx >= 0 && ny >= 0 && nx < row && ny < col && roomNumbers[i][j] != roomNumbers[nx][ny]) {
                        maxCombineSize = Math.max(maxCombineSize, roomSizes.get(roomNumbers[i][j] - 1) + roomSizes.get(roomNumbers[nx][ny] - 1));
                    }
                }
            }
        }

        System.out.println(roomCount);
        System.out.println(maxCount);
        System.out.println(maxCombineSize);
    }

    private static int bfs(int x, int y, int roomCount) {
        Queue<State> queue = new ArrayDeque<>();
        queue.offer(new State(x, y));
        roomNumbers[x][y] = roomCount;
        int count = 0;

        while (!queue.isEmpty()) {
            State curr = queue.poll();
            count++;

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= row || ny >= col || roomNumbers[nx][ny] != 0) continue;

                if (canMove(d, curr)) {
                    queue.offer(new State(nx, ny));
                    roomNumbers[nx][ny] = roomCount;
                }
            }
        }

        return count;
    }

    private static boolean canMove(int d, State curr) {
        if (d == 0 && !wallCheckers[curr.x][curr.y].left) {
            return true;
        } else if (d == 1 && !wallCheckers[curr.x][curr.y].right) {
            return true;
        } else if (d == 2 && !wallCheckers[curr.x][curr.y].top) {
            return true;
        } else if (d == 3 && !wallCheckers[curr.x][curr.y].down) {
            return true;
        }
        return false;
    }
}