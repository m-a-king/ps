package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 17779: 게리맨더링2
 * 1. x, y, d1, d2를 모든 가능한 값으로 완전탐색.
 * 2. 5번 구역 경계선 설정 -> 내부 채우기.
 * 3. 남은 지역을 1~4 구역 부등식에 따라 설정.
 * 4. 각 구역 인구수 계산 -> 최댓값-최솟값 갱신.
 */
public class BJ17779_2 {
    static int N;
    static int[][] map;
    static int totalPopulation;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        totalPopulation = 0;

        for (int r = 1; r <= N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                totalPopulation += map[r][c];
            }
        }

        // 1. x, y, d1, d2 완전탐색
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int d1 = 1; d1 <= N; d1++) {
                    for (int d2 = 1; d2 <= N; d2++) {
                        if (x + d1 + d2 > N) continue;
                        if (y - d1 < 1) continue;
                        if (y + d2 > N) continue;

                        divideAndCalc(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static void divideAndCalc(int x, int y, int d1, int d2) {
        // 5번 구역 표시를 위한 배열
        int[][] area = new int[N + 1][N + 1];

        // 1) 경계선 5로 표시
        for (int i = 0; i <= d1; i++) {
            area[x + i][y - i] = 5;
        }
        for (int i = 0; i <= d2; i++) {
            area[x + i][y + i] = 5;
        }
        for (int i = 0; i <= d2; i++) {
            area[x + d1 + i][y - d1 + i] = 5;
        }
        for (int i = 0; i <= d1; i++) {
            area[x + d2 + i][y + d2 - i] = 5;
        }

        // 2) 5번 구역 채우기
        for (int r = x + 1; r < x + d1 + d2; r++) {
            boolean toggle = false;
            for (int c = 1; c <= N; c++) {
                if (area[r][c] == 5) toggle = !toggle;
                if (toggle) area[r][c] = 5;
            }
        }

        // 3) 1~4번 구역 채우기
        // area[r][c]가 아직 0인 칸을 부등식에 따라 1~4로 채우기
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (area[r][c] != 0) continue;

                // 구역 1
                if (1 <= r && r < x + d1 && 1 <= c && c <= y) {
                    area[r][c] = 1;
                    // 구역 2
                } else if (1 <= r && r <= x + d2 && y < c && c <= N) {
                    area[r][c] = 2;
                    // 구역 3
                } else if (x + d1 <= r && r <= N && 1 <= c && c < y - d1 + d2) {
                    area[r][c] = 3;
                    // 구역 4
                } else if (x + d2 < r && r <= N && (y - d1 + d2) <= c && c <= N) {
                    area[r][c] = 4;
                }
            }
        }

        boolean flag = false;

        for (int r = 1; r <= N; r++) {
            flag = false;
            for (int c = 1; c <= N; c++) {
                if (area[r][c] == 5) {
                    flag = true;
                    continue;
                }

                // 구역 1
                if (!flag && 1 <= r && r < x + d1 && 1 <= c && c <= y) {
                    area[r][c] = 1;
                    // 구역 2
                } else if (flag && 1 <= r && r <= x + d2 && y < c) {
                    area[r][c] = 2;
                    // 구역 3
                } else if (!flag && x + d1 <= r && r <= N && 1 <= c && c < y - d1 + d2) {
                    area[r][c] = 3;
                    // 구역 4
                } else if (flag && x + d2 < r && r <= N && y - d1 + d2 <= c) {
                    area[r][c] = 4;
                }
            }
        }

        // 4) 각 구역 인구수 계산
        int[] population = new int[6];

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                int a = area[r][c];
                population[a] += map[r][c];
            }
        }

        // 최소, 최대
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= 5; i++) {
            max = Math.max(max, population[i]);
            min = Math.min(min, population[i]);
        }
        answer = Math.min(answer, max - min);
    }
}
