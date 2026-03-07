package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ10815 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Set<Integer> checker = new HashSet<>();

        final int N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            checker.add(Integer.parseInt(stringTokenizer.nextToken()));
        }

        final int M = Integer.parseInt(bufferedReader.readLine());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < M; i++) {
            if (checker.contains(Integer.parseInt(stringTokenizer.nextToken()))) {
                answer.append("1 ");
            } else {
                answer.append("0 ");
            }
        }

        System.out.println(answer);
    }
}
