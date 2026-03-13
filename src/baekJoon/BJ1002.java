package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1002 {

    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());

        while (T-- > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            final int x1 = Integer.parseInt(stringTokenizer.nextToken());
            final int y1 = Integer.parseInt(stringTokenizer.nextToken());
            final int r1 = Integer.parseInt(stringTokenizer.nextToken());
            final int x2 = Integer.parseInt(stringTokenizer.nextToken());
            final int y2 = Integer.parseInt(stringTokenizer.nextToken());
            final int r2 = Integer.parseInt(stringTokenizer.nextToken());

            long distance = (long) (x1 - x2) * (x1 - x2) + (long) (y1 - y2) * (y1 - y2);
            long sumR = (long) (r1 + r2) * (r1 + r2);
            long diffR = (long) (r1 - r2) * (r1 - r2);

            if (distance == 0 && r1 == r2) {
                answer.append(-1).append("\n");
                continue;
            }
            if (distance > sumR || distance < diffR) {
                answer.append(0).append("\n");
                continue;
            }
            if (distance == sumR || distance == diffR) {
                answer.append(1).append("\n");
                continue;
            }
            answer.append(2).append("\n");

        }
        System.out.print(answer);
    }
}
