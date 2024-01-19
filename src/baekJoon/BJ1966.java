package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BJ1966 {

    private static class Document{
        int idx, weight;

        public Document(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());



        for (int i = 0; i < n; i++) {
            String[] input = bufferedReader.readLine().split(" ");
            int numCount = parseInt(input[0]);
            int target = parseInt(input[1]);

            List<Document> docs = new ArrayList<>();
            int[] weight = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int idx = 0; idx < numCount; idx++) {
                docs.add(new Document(idx, weight[idx]));
            }

            sortPrinter(docs, numCount);

            int printOrder = 1; // 출력 순서
            for (Document doc : docs) {
                if (doc.idx == target) {
                    System.out.println(printOrder);
                    break;
                }
                printOrder++;
            }

        }

    }

    private static void sortPrinter(List<Document> docs, int numCount) {
        for (int now = 0; now < numCount - 1; now++) {
            for (int next = now + 1; next < numCount; next++) {
                if (docs.get(now).weight < docs.get(next).weight) {
                    docs.add(new Document(docs.get(now).idx, docs.get(now).weight));
                    docs.remove(now--);
                    break;
                }
            }
        }

    }

}
