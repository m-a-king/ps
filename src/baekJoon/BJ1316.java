package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1316 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int answer = 0;

        for (int i = 0; i < n; i++) {
            String word = bufferedReader.readLine();
            boolean[] visited = new boolean[26];
            char pre = word.charAt(0);
            visited[word.charAt(0) - 'a'] = true;
            boolean flag = true;

            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                int seq = c - 'a';

                if (pre == c) {
                    continue;
                }
                if (visited[seq]) {
                    flag = false;
                    break;
                }
                pre = c;
                visited[seq] = true;
            }
            if (flag) {
                answer++;
            }

        }

        System.out.println(answer);

    }
}
