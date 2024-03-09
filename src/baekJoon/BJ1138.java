package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1138 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        int[] res = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int pos = Integer.parseInt(stringTokenizer.nextToken()) + 1;
            int count = 0; // 빈칸 카운트
            for (int j = 1; j <= n; j++) {
                if (res[j] == 0) {
                    count++;
                    if (count == pos) {
                        res[j] = i;
                        break;
                    }
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            stringBuilder.append(res[i]).append(" ");
        }
        System.out.println(stringBuilder);

    }
}
