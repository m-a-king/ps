package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ5597 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        boolean[] visited = new boolean[31];

        for (int i = 0; i < 28; i++) {
            int n = Integer.parseInt(bufferedReader.readLine());
            visited[n] = true;
        }

        for (int i = 1; i < 31; i++) {
            if(visited[i]) continue;
            System.out.println(i);
        }

    }
}
