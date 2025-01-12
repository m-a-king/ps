package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11723 {

    private static int bit = 0;
    private static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(bufferedReader.readLine());

        while (tc-- > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            process(stringTokenizer);
        }

        System.out.println(stringBuilder);
    }

    private static void process(StringTokenizer stringTokenizer) {
        String cmd = stringTokenizer.nextToken();

        if (cmd.equals("all")) {
            bit |= (1 << 21) - 1;
            return;
        }

        if (cmd.equals("empty")) {
            bit = 0;
            return;
        }

        int x = Integer.parseInt(stringTokenizer.nextToken());
        int temp = 1 << x;


        if (cmd.equals("add")) {
            bit |= temp;
            return;
        }

        if (cmd.equals("remove")) {
            bit &= ~temp;
            return;
        }

        if (cmd.equals("check")) {
            if ((bit & temp) == temp) {
                stringBuilder.append(1);
            } else {
                stringBuilder.append(0);
            }
            stringBuilder.append("\n");
            return;
        }

        if (cmd.equals("toggle")) {
            bit ^= temp;
            return;
        }

    }
}
