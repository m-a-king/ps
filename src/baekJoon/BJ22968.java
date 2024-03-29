package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ22968 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 최대 높이 계산
        // V의 최대 입력 값 1_000_000_000로 만들 수 있는 최대 높이 -> 42
        // 1_000_000_000 개의 정점은 높이 41개는 충분히 쌓고 43개는 쌓지 못한다.
        // 트리의 높이(1 ~ 43)을 미리 저장
        int[] maxHeight = new int[44];
        maxHeight[1] = 1;
        maxHeight[2] = 2;
        maxHeight[3] = 4;
        maxHeight[4] = 7;

        for (int i = 5; i < maxHeight.length; i++) {
            maxHeight[i] = maxHeight[i - 2] + maxHeight[i - 1] + 1;
        }

        int t = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        // 테스트 케이스만큼 반복
        for (int i = 0; i < t; i++) {
            int v = Integer.parseInt(bufferedReader.readLine());

            // 이진 탐색 알고리즘
            int left = 1;
            int right = maxHeight.length - 1; // 43 (저장해둔 트리 높이)

            // 이진 탐색에서 left 가 right 보다 크면 안됨
            while (left <= right) {

                // mid = left + right 와 동일한 값
                // 오버플로우 방지
                int mid = left + (right - left) / 2;

                // 입력된 정점의 개수로 mid 만큼의 높이를 갖는 트리를 만들 수 있다면?
                if (maxHeight[mid] <= v) {

                    // 그리고 입력된 정점의 개수로 (mid + 1) 만큼의 높이를 갖는 트리를 만들 수 없다면?
                    // 즉, 입력된 정점의 개수로는 mid 만큼의 높이를 갖는 트리는 만들 수 있고,
                    // (mid + 1) 만큼의 높이를 갖는 트리는 만들 수 없다.
                    // 조건 만족 -> mid 기록
                    if (maxHeight[mid +1] > v) {
                        stringBuilder.append(mid).append("\n");
                        break;

                    // 그리고 입력된 정점의 개수로 (mid + 1) 만큼의 높이를 갖는 트리를 만들 수 있다면?
                    // mid 값이 커져야한다.
                    // 즉, left 의 증가
                    } else {
                        left = mid + 1;
                    }

                // 입력된 정점의 개수로 mid 만큼의 높이를 갖는 트리를 만들 수 없다면?
                // mid 값이 작아져야한다.
                // 즉, right 의 증가
                } else {
                    right = mid - 1;

                }


            }
        }

        // 결과 출력
        System.out.println(stringBuilder);
    }
}
