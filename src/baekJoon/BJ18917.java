package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ18917 {

    private static class QueryProcessor {

        private long sum = 0;
        private long xorSum = 0;

        public long getSum() {
            return sum;
        }

        public long getXorSum() {
            return xorSum;
        }

        public void command(int select, int x) {
            xorSum ^= x;

            if (select == 1) {
                add(x);
                return;
            }

            remove(x);
        }

        private void add(int x) {
            sum += x;
        }

        private void remove(int x) {
            sum -= x;
        }
    }

    public static void main(String[] args) throws IOException {

        QueryProcessor processor = new QueryProcessor();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int m = Integer.parseInt(bufferedReader.readLine());

        while (m-- > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int select = Integer.parseInt(stringTokenizer.nextToken());

            if (select == 1 || select == 2) {
                int x = Integer.parseInt(stringTokenizer.nextToken());

                processor.command(select, x);
                continue;
            }

            if (select == 3) {
                result.append(processor.getSum()).append("\n");
            }

            if (select == 4) {
                result.append(processor.getXorSum()).append("\n");
            }
        }

        System.out.println(result.toString().trim());
    }
}
