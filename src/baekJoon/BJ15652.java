package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ15652 {

    static int row;
    static int col;
    static StringBuilder stringBuilder = new StringBuilder();


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bufferedReader.readLine().split(" ");

        row = parseInt(input[0]);
        col = parseInt(input[1]);


        dfs(1,0);

    }

    private static void dfs(int start, int depth) {

        if (depth == col) {
            System.out.println(stringBuilder.toString().trim());
            return;
        }

        for (int i = start; i <= row; i++) {
            stringBuilder.append(i).append(" ");
            dfs(i, depth + 1);
            stringBuilder.setLength(stringBuilder.length() - 2);
        }
    }
}
