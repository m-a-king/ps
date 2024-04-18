package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14889 {

    static int[][] map;
    static boolean[] visited;
    static int n;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());

        map = new int[n][n];
        visited = new boolean[n];


        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }


        selectTeam(0, 0);

        System.out.println(minDiff);

    }

    private static void selectTeam(int current, int depth) {

        if (depth == n / 2) {
            calcDiff();
            return;
        }

        for (int i = current; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selectTeam(i + 1, depth + 1);
                visited[i] = false;
            }
        }
    }

    private static void calcDiff() {
        int start = 0;
        int link = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!visited[i] && !visited[j]) {
                    start += map[i][j] + map[j][i];
                } else if (visited[i] && visited[j]) {
                    link += map[i][j] + map[j][i];
                }
            }
        }

        int diff = Math.abs(start - link);
        if (diff == 0) {
            System.out.println(0);
            System.exit(0);

        }
        minDiff = Math.min(minDiff, diff);

    }
}
