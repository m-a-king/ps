package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ11729 {

    static StringBuilder stringBuilder = new StringBuilder();
    static StringBuilder checker = new StringBuilder();
    static int count = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());


        hanoi(n, 1, 2, 3);


        System.out.println(count);
        System.out.println(stringBuilder);

        System.out.println(checker);

    }

    private static void hanoi(int n, int start, int mid, int end) {


//        checker.append("hanoi 함수의 ").append(count+1).append("번째 호출 -> ").append(n).append("개의 판을 ").append(start).append("에서 ").append(mid).append("를 거쳐서 ")
//                .append(end).append("로 향하게 한다").append("\n");

        count++;

        if (n == 1) {
            stringBuilder.append(start).append(" ").append(end).append("\n");
        } else {
            hanoi(n - 1, start, end, mid);
            stringBuilder.append(start).append(" ").append(end).append("\n");
            hanoi(n - 1, mid, start, end);
        }
    }
}
