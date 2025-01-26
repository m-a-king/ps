package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ15828 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        int[] queue = new int[N];
        int header = 0;
        int pointer = 0;
        int size = 0;

        while (true) {
            int info = Integer.parseInt(bufferedReader.readLine());

            if (info == -1) {
                break;
            }

            if (info == 0) {
                header++;
                size--;
                continue;
            }

            if (size == N) {
                continue;
            }

            queue[pointer % N] = info;
            pointer++;
            size++;
        }

        if (size == 0) {
            System.out.println("empty");
            return;
        }

        StringBuilder result = new StringBuilder();

        for (int i = header; i < header + size; i++) {
            result.append(queue[i % N]).append(" ");
        }

        System.out.println(result.toString().trim());
    }
}
