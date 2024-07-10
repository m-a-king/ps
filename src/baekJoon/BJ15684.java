package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15684 {
    static int n, m, h;

    static boolean[][] hMap;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        n = Integer.parseInt(stringTokenizer.nextToken()); // 세로 축 개수
        m = Integer.parseInt(stringTokenizer.nextToken()); // 기존의 가로 선 개수
        h = Integer.parseInt(stringTokenizer.nextToken()); // 가로 축 개수 (점선 축 개수)
        if (m == 0) {
            System.out.println(0);
            return;
        }

        // row: 1~h
        // col: 1~(n-1)
        hMap = new boolean[h + 1][n];

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int row = Integer.parseInt(stringTokenizer.nextToken());
            int col = Integer.parseInt(stringTokenizer.nextToken());

            // row 행의
            // col 열과 col+1 열이 연결됨
            hMap[row][col] = true;
        }

        // 추가되는 선의 개수를 제한 (0, 1, 2, 3)
        for (int limit = 0; limit <= 3; limit++) {
            // *** true 반환받으면 즉시 종료 ***
            if (simulate(0, 1, 1, limit)) {
                System.out.println(limit);
                return;
            }
        }

        // 0, 1, 2, 3 으로 해결하지 못한다면
        System.out.println(-1);

    }

    private static boolean simulate(int depth, int sRow, int sCol, int limit) {
        // 추가된 가로선의 개수가 제한한 개수와 같다면
        // i번에서 출발해서 i번으로 도착하는지 확인
        if (depth == limit) {
            // check 의 반환에 따라서
            // 실패 --> false 반환
            // 성공 --> true 반환
            // *** 반환을 받기 위해서 호출을 조건으로 감싸야함 ***
            return check();
        }

        for (int i = sRow; i <= h; i++) {
            for (int j = (i == sRow ? sCol : 1); j < n; j++) {
                // 이미 사다리가 있는 자리는 사다리 설치 불가
                if (hMap[i][j]) continue;

                // 배열 범위 검사 이후,
                // 내 왼쪽에 사다리가 있으면 사다리 설치 불가
                if (1 < j && hMap[i][j - 1]) continue;

                // 배열 범위 검사 이후,
                // 내 오른쪽에 사다리가 있다면 사다리 설치 불가
                if (j < n - 1 && hMap[i][j + 1]) continue;

                // 정상적으로 설치
                hMap[i][j] = true;

                // *** true 반환받으면 즉시 종료 ***
                if (simulate(depth + 1, i, j + 1, limit)) {
                    return true;
                }

                // 위 반환이 false 였다면, 백트래킹 (사다리 설치한거 없애기)
                hMap[i][j] = false;
            }
        }

        return false;
    }

    private static boolean check() {
        // 1번 세로축부터 n번 세로축까지 확인
        for (int start = 1; start <= n; start++) {

            // 시작 세로축(열)
            int col = start;

            // 시작 가로축 = 1, 도착 가로축 = h
            for (int row = 1; row <= h; row++) {
                // 배열 범위 검사 이후,
                // 현재 오른쪽으로 이동 가능한 사다리가 있다면
                if (col < n && hMap[row][col]) {
                    col++;
                }

                // 배열 범위 검사 이후,
                // 현재 왼쪽으로 이동 가능한 사다리가 있다면
                else if (col > 1 && hMap[row][col - 1]) {
                    col--;
                }
            }

            // 시작점과 도착점이 같지 않다면 즉시 실패로 종료
            if (start != col) {
                return false;
            }
        }

        // n번의 반복이 정상적으로 진행되었다면 성공
        return true;
    }

    // 테스트용 출력
    private static void printMap() {
        for (int i = 1; i < hMap.length; i++) {
            for (int j = 1; j < hMap[0].length; j++) {
                System.out.print(" | " + (hMap[i][j] ? "--" : "  "));

            }
            System.out.print(" | ");
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
