package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

public class BJ14502 {

    static int n, m;
    static int[][] map;
    static int[] change;

    static int maxCount = 0;

    private static class Node {
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");

        n = parseInt(nm[0]);
        m = parseInt(nm[1]);
        map = new int[n][m];
        List<Integer> changeable = new ArrayList<>();


        StringTokenizer stringTokenizer;
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 0; j < m; j++) {
                int current = parseInt(stringTokenizer.nextToken());
                map[i][j] = current;
                if (current == 0) {
                    changeable.add(m * i + j);
                }
            }
        }

        int[] row = new int[3];
        int[] col = new int[3];

        for (int i = 0; i < changeable.size() - 2; i++) {
            int iIndex = changeable.get(i);
            row[0] = iIndex / m;
            col[0] = iIndex % m;
            for (int j = i + 1; j < changeable.size() - 1; j++) {
                int jIndex = changeable.get(j);
                row[1] = jIndex / m;
                col[1] = jIndex % m;
                for (int k = j + 1; k < changeable.size(); k++) {
                    int kIndex = changeable.get(k);
                    row[2] = kIndex / m;
                    col[2] = kIndex % m;


                    int[][] originalMap = copyMap();

                    map[row[0]][col[0]] = 1;
                    map[row[1]][col[1]] = 1;
                    map[row[2]][col[2]] = 1;

                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < m; y++) {
                            if (map[x][y] == 2) {
                                bfs(new Node(x, y));
                            }
                        }
                    }

                    int count = 0;
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < m; y++) {
                            if (map[x][y] == 0) {
                                count++;
                            }
                        }
                    }

                    maxCount = Math.max(maxCount, count);

                    map = originalMap;

                }
            }
        }


        System.out.println(maxCount);

    }

    private static int[][] copyMap() {
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, copy[i], 0, map[i].length);
        }
        return copy;
    }

    private static boolean isSafe(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < m;
    }

    private static void bfs(Node start) {
        boolean[][] visited = new boolean[n][m];
        visited[start.row][start.col] = true;

        Queue<Node> queue = new ArrayDeque<>();

        queue.offer(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            for (int k = 0; k < 4; k++) {
                int nextRow = current.row + dx[k];
                int nextCol = current.col + dy[k];
                if (isSafe(nextRow, nextCol)) {
                    if (map[nextRow][nextCol] == 0 && !visited[nextRow][nextCol]) {
                        visited[nextRow][nextCol] = true;
                        map[nextRow][nextCol] = 2;
                        queue.offer(new Node(nextRow, nextCol));
                    }
                }
            }
        }
    }
}
