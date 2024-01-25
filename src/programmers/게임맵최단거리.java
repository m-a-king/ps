package programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class 게임맵최단거리 {

    static boolean[][] visited;
    static int n, m;

    private class Node {
        int x, y, time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public int solution(int[][] maps) {
        int answer = 0;

        n = maps.length;
        m = maps[0].length;

        visited = new boolean[n][m];
        visited[0][0] = true;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0, 1));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.x == n - 1 && current.y == m - 1) {
                answer = current.time;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];
                if (isSafe(nextX, nextY, maps) && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    queue.offer(new Node(nextX, nextY, current.time + 1));
                }
            }
        }

        return answer == 0 ? -1 : answer;
    }


    private boolean isSafe(int row, int col, int[][] maps) {
        return 0 <= row && row < n && 0 <= col && col < m && maps[row][col] == 1;
    }
}
