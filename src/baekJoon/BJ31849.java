package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ31849 {

    private static class Pos {
        int row, col, cost;

        public Pos(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        int mapRow = Integer.parseInt(stringTokenizer.nextToken());
        int mapCol = Integer.parseInt(stringTokenizer.nextToken());
        int roomCnt = Integer.parseInt(stringTokenizer.nextToken());
        int storeCnt = Integer.parseInt(stringTokenizer.nextToken());

        List<Pos> roomList = new ArrayList<>();
        Queue<Pos> storeQueue = new LinkedList<>();

        for (int i = 0; i < roomCnt; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int roomRow = Integer.parseInt(stringTokenizer.nextToken());
            int roomCol = Integer.parseInt(stringTokenizer.nextToken());
            int roomCost = Integer.parseInt(stringTokenizer.nextToken());

            roomList.add(new Pos(roomRow, roomCol, roomCost));// 코스트는 월세
        }

        boolean[][] visited = new boolean[mapRow + 1][mapCol + 1];
        int[][] distance = new int[mapRow + 1][mapCol + 1];

        for (int i = 0; i < storeCnt; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int storeRow = Integer.parseInt(stringTokenizer.nextToken());
            int storeCol = Integer.parseInt(stringTokenizer.nextToken());

            storeQueue.add(new Pos(storeRow, storeCol, 0)); // 코스트는 거리
            visited[storeRow][storeCol] = true;
        }

        while (!storeQueue.isEmpty()) {
            Pos current = storeQueue.poll();

            for (int i = 0; i < 4; i++) {
                int newRow = current.row + dx[i];
                int newCol = current.col + dy[i];

                if (newRow > 0 && newRow <= mapRow && newCol > 0 && newCol <= mapCol && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    distance[newRow][newCol] = current.cost + 1;
                    storeQueue.add(new Pos(newRow, newCol, current.cost + 1));
                }
            }
        }

        int minPenseScore = Integer.MAX_VALUE; // 편세권 점수

        for (Pos room : roomList) {
            int roomRow = room.row;
            int roomCol = room.col;
            int roomCost = room.cost;

            int penseScore = distance[roomRow][roomCol] * roomCost;
            if (penseScore < minPenseScore) {
                minPenseScore = penseScore;
            }
        }

        System.out.println(minPenseScore);
    }
}
