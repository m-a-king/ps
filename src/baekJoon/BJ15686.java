package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class BJ15686 {

    private static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static List<Pos> home;
    static List<Pos> chicken;
    static int[][] map;
    static int m;
    static int result = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[n][n];
        home = new ArrayList<>();
        chicken = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] == 1) {
                    home.add(new Pos(i, j));
                } else if (map[i][j] == 2) {
                    chicken.add(new Pos(i, j));
                }
            }
        }

        deleteChicken(0);

        System.out.println(result);


    }

    static List<Integer> chickenDelList = new ArrayList<>();

    private static void deleteChicken(int current) {

        if (chickenDelList.size() == m) {
            result = Math.min(result, calcChickenDis());
            return;
        }

        for (int i = current; i < chicken.size(); i++) {
            chickenDelList.add(i);
            deleteChicken(i + 1);
            chickenDelList.remove(chickenDelList.size() - 1);

        }

    }

    private static int calcChickenDis() {
        int total = 0;

        for (int i = 0; i < home.size(); i++) {
            Pos currentHome = home.get(i);
            int currentDis = Integer.MAX_VALUE;

            for (int j = 0; j < chicken.size(); j++) {
                if (chickenDelList.contains(j)) {
                    Pos currentChicken = chicken.get(j);

                    currentDis = Math.min(Math.abs(currentHome.x - currentChicken.x) + Math.abs(currentHome.y - currentChicken.y), currentDis);
                }
            }
            total += currentDis;
        }
        return total;
    }
}
