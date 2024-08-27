package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ31403 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 세 정수 A, B, C 입력
        int A = Integer.parseInt(bufferedReader.readLine());
        int B = Integer.parseInt(bufferedReader.readLine());
        int C = Integer.parseInt(bufferedReader.readLine());

        // 결과 출력
        System.out.println(A + B - C);
        String AB = A + String.valueOf(B);
        System.out.println(Integer.parseInt(AB) - C);
    }
}
