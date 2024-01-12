package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class BJ2775 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tc = parseInt(bufferedReader.readLine());

        Map<String, Integer> information = new HashMap<>();

        for (int i = 1; i <= 14; i++) {
            information.put(0 + " " + i, i);
        }

        for (int i = 0; i < tc; i++) {
            int floor = parseInt(bufferedReader.readLine());
            int room = parseInt(bufferedReader.readLine());

            System.out.println(calcPeople(information, floor, room));
        }
    }

    private static int calcPeople(Map<String, Integer> information, int floor, int room) {

        if (floor == 0) {
            return room;
        }

        if (room == 1) {
            return 1;
        }

        String key = floor + " " + room;
        if (information.containsKey(key)) {
            return information.get(key);
        }

        int count = 0;
        for (int i = 1; i <= room; i++) {
            count += calcPeople(information, floor - 1, i);
        }
        information.put(key, count);

        return count;
    }

}
