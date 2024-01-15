package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Collections.sort;

public class BJ11651 {

    static class Pos implements Comparable<Pos> {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pos o) {
            if (this.y == o.y) {
                return this.x - o.x;
            } else {
                return this.y - o.y;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());
        List<Pos> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] s = bufferedReader.readLine().split(" ");
            list.add(new Pos(parseInt(s[0]), parseInt(s[1])));
        }

        sort(list);
        for (Pos p : list) {
            System.out.println(p.x + " " + p.y);
        }
    }
}
