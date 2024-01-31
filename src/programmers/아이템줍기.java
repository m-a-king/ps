package programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class 아이템줍기 {

    private class Pos {
        int x, y, cost;

        public Pos(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }


    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        int[][] map = new int[102][102];
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;

        for (int i = 0; i < rectangle.length; i++) {
            drawMap(map, rectangle[i]);
        }
        for (int i = 0; i < rectangle.length; i++) {
            eraseMap(map, rectangle[i]);
        }

        Queue<Pos> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[102][102];
        queue.offer(new Pos(characterX, characterY,0));
        visited[characterY][characterX] = true;

        while (!queue.isEmpty()) {
            Pos current = queue.poll();

            if (current.x == itemX && current.y == itemY) {
                answer = current.cost/2;
                break;
            }

            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];

                if (nextX >= 0 && nextX < 102 && nextY >= 0 && nextY < 102 && map[nextY][nextX] == 1 && !visited[nextY][nextX]) {
                    queue.offer(new Pos(nextX, nextY,current.cost+1));
                    visited[nextY][nextX] = true;
                }
            }
        }
        return answer;
    }

    private void eraseMap(int[][] map, int[] rectangle) {
        int startX = 2 * rectangle[0];
        int startY = 2 * rectangle[1];
        int endX = 2 * rectangle[2];
        int endY = 2 * rectangle[3];
        for (int y = startY + 1; y < endY; y++) {
            for (int x = startX + 1; x < endX; x++) {
                map[y][x] = 0;
            }
        }
    }

    private void drawMap(int[][] map, int[] rectangle) {
        int startX = 2 * rectangle[0];
        int startY = 2 * rectangle[1];
        int endX = 2 * rectangle[2];
        int endY = 2 * rectangle[3];
        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                map[y][x] = 1;
            }
        }
    }
}
