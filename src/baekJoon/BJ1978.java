package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BJ1978 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = parseInt(bufferedReader.readLine());

        int[] numbers = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int number : numbers) {

            if (number == 1) {
                N--;
                continue;
            }
            int x = number - 1;
            while (x > 1) {
                if(number%x == 0){
                    N--;
                    break;
                }
                x--;
            }
        }

        System.out.println(N);
    }
}

//private static boolean isPrime(int number) {
//    if (number < 2) return false; // 1은 소수가 아님
//    if (number == 2) return true; // 2는 소수임
//    if (number % 2 == 0) return false; // 2를 제외한 모든 짝수는 소수가 아님
//    for (int i = 3; i <= Math.sqrt(number); i += 2) { // 홀수만 체크
//        if (number % i == 0) {
//            return false;
//        }
//    }
//    return true;
//}
