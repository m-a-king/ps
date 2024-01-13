package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import static java.lang.Integer.parseInt;


public class BJ10026 {

    static String[][] grid;
    static String[][] gridRG;
    static boolean[][] visited;
    static int n;


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = parseInt(bufferedReader.readLine());

        grid = new String[n][n];
        gridRG = new String[n][n];

        for (int i = 0; i < n; i++) {
            String[] line = bufferedReader.readLine().split("");
            for (int j = 0; j < n; j++) {
                grid[i][j] = line[j];
                if (Objects.equals(line[j], "R") || Objects.equals(line[j], "G")) {
                    gridRG[i][j] = "F";
                } else {
                    gridRG[i][j] = line[j];
                }
            }
        }

        visited = new boolean[n][n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, grid[i][j], 1);
                    count++;
                }
            }
        }

        visited = new boolean[n][n];
        int countRG = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, gridRG[i][j], 2);
                    countRG++;
                }
            }
        }

        System.out.println(count);
        System.out.println(countRG);
    }

    private static void dfs(int i, int j, String target, int mode) {

        if (visited[i][j] || (mode == 1 && !grid[i][j].equals(target)) || (mode == 2 && !gridRG[i][j].equals(target))) {
            return;
        }

        visited[i][j] = true;

        if (i - 1 >= 0) dfs(i - 1, j, target, mode);
        if (i + 1 < n) dfs(i + 1, j, target, mode);
        if (j - 1 >= 0) dfs(i, j - 1, target, mode);
        if (j + 1 < n) dfs(i, j + 1, target, mode);

    }
}
