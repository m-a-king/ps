package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1158 {

    static boolean[] visited;
    static int n, k, idx;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<");

        visited = new boolean[n + 1];

        idx = 0;
        while (count != n) {

            int step = 0;
            while (step != k) {
                idx = (idx + 1) % n;
                if (!visited[idx]) {
                    step++;
                }
            }
            visited[idx] = true;
            count++;

            stringBuilder.append(idx == 0 ? n : idx).append(", ");
        }

        stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());
        stringBuilder.append(">");
        System.out.println(stringBuilder);
    }

}
