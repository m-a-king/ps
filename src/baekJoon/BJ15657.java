package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class BJ15657 {

    static int n,m;
    static int[] numbers;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = bufferedReader.readLine().split(" ");

        n = parseInt(nm[0]);
        m = parseInt(nm[1]);

        numbers = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        dfs(0, 0);

    }

    private static void dfs(int current, int depth) {

        if (depth == m) {
            System.out.println(stringBuilder.toString().trim());

            return;
        }

        for (int i = current; i < n; i++) {
            int lengthBeforeAdd = stringBuilder.length(); // 숫자를 추가하기 전의 길이 저장
            stringBuilder.append(numbers[i]).append(" ");
            dfs(i, depth + 1); // 다음 깊이로 탐색 (i를 current 자리에 넣어야 중복 허용)
            stringBuilder.setLength(lengthBeforeAdd); // 저장된 길이로 다시 설정하여 정확히 제거
        }


    }
}
