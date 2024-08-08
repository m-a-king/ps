package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class BJ7432 {

    static StringBuilder stringBuilder = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        TreeMap<String, TreeMap> paths = new TreeMap<>();

        while (n-- > 0) {
            String path = bufferedReader.readLine();

            String[] splitPath = path.split("\\\\");
            TreeMap<String, TreeMap> pointer = paths;
            for (String part : splitPath) {
                pointer = pointer.computeIfAbsent(part, key -> new TreeMap<String, TreeMap>());
            }
        }

        printPaths(paths, 0);

        System.out.println(stringBuilder.toString().trim());
    }

    private static void printPaths(TreeMap<String, TreeMap> currPaths, int space) {
        for (String key : currPaths.keySet()) {
            for (int i = 0; i < space; i++) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(key).append("\n");
            printPaths(currPaths.get(key), space + 1);
        }
    }
}
