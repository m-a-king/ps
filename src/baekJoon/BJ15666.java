package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BJ15666 {

    static int n, m;
    static int[] numbers;

    static int[] res;

    static StringBuilder stringBuilder = new StringBuilder();


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");

        n = parseInt(nm[0]);
        m = parseInt(nm[1]);

        res = new int[m];

        numbers = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        dfs(0, 0);

        System.out.println(stringBuilder);

    }

    private static void dfs(int current, int depth) {

        if (depth == m) {
            for (int i = 0; i < m; i++) {
                stringBuilder.append(res[i]).append(" ");
            }
            stringBuilder.append("\n");
            return;
        }
        int pre = -1;
        for (int i = current; i < n; i++) {
            if (numbers[i] != pre) {
                pre = numbers[i];
                res[depth] = numbers[i];
                dfs(i, depth + 1);
            }
        }
    }


}
