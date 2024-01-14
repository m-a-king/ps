package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BJ7568 {

    static class Body {
        int cm, kg, rank;

        public Body(int cm, int kg, int rank) {
            this.cm = cm;
            this.kg = kg;
            this.rank = 1;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());

        List<Body> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] s = bufferedReader.readLine().split(" ");
            list.add(new Body(parseInt(s[0]), parseInt(s[1]), i + 1));
        }

        for (Body b1 : list) {
            for (Body b2 : list
            ) {
                if (b1 != b2 && b1.kg > b2.kg && b1.cm > b2.cm) {
                    b2.rank++;
                }
            }
        }

        for (Body b : list) {
            System.out.print(b.rank + " ");
        }


    }
}
