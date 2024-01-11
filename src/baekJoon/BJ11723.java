package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class BJ11723 {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());

        Set<Integer> list = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String[] input = bufferedReader.readLine().split(" ");

            if (input.length == 2) {
                String command = input[0];
                int number = parseInt(input[1]);
                action(command, number, list, sb);
            } else {
                String command = input[0];
                action(command, -1, list, sb);
            }
        }

        System.out.println(sb);
    }

    private static void action(String command, int n, Set<Integer> list, StringBuilder stringBuilder) {
        if (command.equals("add")) {
            list.add(n);
        } else if (command.equals("remove")) {
            list.remove(n);
        } else if (command.equals("check")) {
            if (list.contains(n)) {
                stringBuilder.append(1 + "\n");
            } else {
                stringBuilder.append(0 + "\n");
            }
        } else if (command.equals("toggle")) {
            if (list.contains(n)) {
                list.remove(n);
            } else {
                list.add(n);
            }
        } else if (command.equals("all")) {
            list.clear();
            for (int i = 1; i <= 20; i++) {
                list.add(i);
            }
        } else if (command.equals("empty")) {
            list.clear();
        }
    }
}
