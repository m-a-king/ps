package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ16466 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(Integer.valueOf(stringTokenizer.nextToken()));
        }
        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == i + 1) continue;
            System.out.println(i + 1);
            return;
        }

        System.out.println(list.size() + 1);
    }
}
