package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ15651 {

    static int n,m;
    static StringBuilder stringBuilder = new StringBuilder();
    static Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        selectNum(0, 0);

        System.out.println(stringBuilder);
    }

    private static void selectNum(int current, int depth) {
        if (depth == m) {
            for (int num : stack) {
                stringBuilder.append(num).append(" ");
            }
            stringBuilder.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            stack.push(i);
            selectNum(i, depth + 1);
            stack.pop();
        }
    }
}
