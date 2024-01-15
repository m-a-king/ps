package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BJ5430 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tc = parseInt(bufferedReader.readLine());

        for (int t = 0; t < tc; t++) {
            String[] commands = bufferedReader.readLine().split("");
            String inputCount = bufferedReader.readLine();
            String input = bufferedReader.readLine();
            input = input.substring(1, input.length() - 1); // 대괄호 제거

            int[] numbers;

            if (input.equals("")) {
                numbers = new int[0]; // 빈 배열 처리
            } else {
                numbers = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
            }

            boolean direction = true; // t: 정방향, f: 역방향
            int tCount = 0;
            int fCount = 0;

            boolean flag = false;

            for (String command : commands) {
                if (command.equals("R")) {
                    direction = !direction;
                } else if (command.equals("D")) {
                    if (tCount + fCount >= numbers.length) {
                        System.out.println("error");
                        flag = true;
                        break;
                    }
                    if (direction) {
                        tCount++;
                    } else {
                        fCount++;
                    }
                }
            }

            if (flag) {
                continue;
            }

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");


            if (tCount + fCount < numbers.length) {
                if (direction) {
                    for (int i = tCount; i < numbers.length - fCount; i++) {
                        stringBuilder.append(numbers[i]);
                        stringBuilder.append(",");

                    }
                } else {
                    for (int i = numbers.length - fCount - 1; i >= tCount; i--) {
                        stringBuilder.append(numbers[i]);
                        stringBuilder.append(",");

                    }
                }
            }

            int length = stringBuilder.length();
            if (length > 1) {
                stringBuilder.delete(length - 1, length);
            }

            stringBuilder.append("]");

            System.out.println(stringBuilder);
        }
    }
}
