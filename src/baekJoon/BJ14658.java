package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ14658 {

    private static class Star {
        int x, y;

        public Star(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m, l, k;
    static List<Star> stars;

    static int[] dx = {1, -1, 1, -1};
    static int[] dy = {1, 1, -1, -1};
    static int maxCount = 0;


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        l = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());
        stars = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            stars.add(new Star(x, y));
        }

        for (Star star1 : stars) {
            for (Star star2 : stars) {
                findSurrounding(star1, star2);

            }
        }

        System.out.println(k - maxCount);
    }


    private static void findSurrounding(Star star1, Star star2) {

        int count = 0;
        for (Star curr : stars) {
            if (star1.x <= curr.x && curr.x <= star1.x + l && star2.y <= curr.y && curr.y <= star2.y + l) {
                count++;
            }
        }
        maxCount = Math.max(maxCount, count);

    }
}
