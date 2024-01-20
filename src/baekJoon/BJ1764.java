package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BJ1764 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        int noListenCount = parseInt(input[0]);
        int noWatchCount = parseInt(input[1]);

        HashSet<String> noListen = new HashSet<>();

        for (int i = 0; i < noListenCount; i++) {
            String person = bufferedReader.readLine();
            noListen.add(person);
        }

        List<String> noListenWatch = new ArrayList<>();

        for (int i = 0; i < noWatchCount; i++) {
            String person = bufferedReader.readLine();
            if (noListen.contains(person)) {
                noListenWatch.add(person);
            }
        }

        Collections.sort(noListenWatch);

        System.out.println(noListenWatch.size());

//        StringBuilder stringBuilder = new StringBuilder();

        for (String person : noListenWatch) {
            System.out.println(person);
//            stringBuilder.append(person);
//            stringBuilder.append("\n");
        }

//        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

//        System.out.println(stringBuilder);


    }
}
