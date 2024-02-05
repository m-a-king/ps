package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ14502 {

    static int n, m;
    static int[][] map;
    static List<Node> viruses = new ArrayList<>();
    static int maxSafeArea = 0;
    static List<Integer> emptySpaces = new ArrayList<>();

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

        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    emptySpaces.add(i * m + j);
                } else if (map[i][j] == 2) {
                    viruses.add(new Node(i, j));
                }
            }
        }

        placeWallsAndSimulate(0, 0);
        System.out.println(maxSafeArea);
    }

    private static void placeWallsAndSimulate(int start, int depth) {
        if (depth == 3) {
            simulate();
            return;
        }

        for (int i = start; i < emptySpaces.size(); i++) {
            int pos = emptySpaces.get(i);
            int row = pos / m;
            int col = pos % m;

            map[row][col] = 1;
            placeWallsAndSimulate(i + 1, depth + 1);
            map[row][col] = 0;
        }
    }

    private static void simulate() {
        int[][] tempMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, tempMap[i], 0, m);
        }

        for (Node virus : viruses) {
            spreadVirus(virus.row, virus.col, tempMap);
        }

        maxSafeArea = Math.max(maxSafeArea, calculateSafeArea(tempMap));
    }

    private static void spreadVirus(int x, int y, int[][] copyMap) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < n && ny < m && copyMap[nx][ny] == 0) {
                copyMap[nx][ny] = 2;
                spreadVirus(nx, ny, copyMap);
            }
        }
    }

    private static int calculateSafeArea(int[][] tempMap) {
        int safe = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tempMap[i][j] == 0) {
                    safe++;
                }
            }
        }
        return safe;
    }
}
