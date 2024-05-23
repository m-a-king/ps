package softeer;

import java.util.*;
import java.io.*;

public class GarageGame {

    static int N;
    static int[][] map;
    static int[] pointers;
    static int answer;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = 3 * N;
        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[M - 1 - i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pointers = new int[N];          // 위에서 떨어질 차량을 가리키는 위칫값
        int[][] garage = new int[N][N];    // 차고 (아래 n*n)
        answer = 0;

        simulate(garage, 0, 1, 0, 0, N - 1);

        System.out.println(answer);
    }

    static void simulate(int[][] garage, int score, int depth, int bottom, int left, int right) {

        if (depth == 4) {
            answer = Math.max(answer, score);
            return;
        }

        initGarage(garage, bottom, left, right);
        int[][] nextGarage = new int[N][N];
        for (int i = 0; i < N; i++) {
            nextGarage[i] = Arrays.copyOf(garage[i], N);
        }
        int[] savedPointers = Arrays.copyOf(pointers, N);

        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j])
                    continue;
                int[] res = bfs(i, j, visited, nextGarage);
                int plus = res[0]; // 이번 깊이의 점수 획득
                bottom = res[1];
                left = res[2];
                right = res[3];
                simulate(nextGarage, score + plus, depth + 1, bottom, left, right);
                copyPointers(savedPointers, left, right);
                copyBox(nextGarage, garage, bottom, left, right);
            }
        }
    }

    static int[] bfs(int i, int j, boolean[][] visited, int[][] garage) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        int target = garage[i][j];
        int count = 1; // 탐색 개수

        int minX = 16, minY = 16, maxX = 0, maxY = 0;
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];
            garage[x][y] = 0;
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (isSafe(nx, ny) || target != garage[nx][ny] || visited[nx][ny]) {
                    continue;
                }
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                count++;
            }
        }

        int rectSize = (maxX - minX + 1) * (maxY - minY + 1); // 탐색을 포함하는 가장 작은 직사각형 넓이

        return new int[]{count + rectSize, minX, minY, maxY};
    }

    private static boolean isSafe(int nx, int ny) {
        return nx < 0 || nx >= N || ny < 0 || ny >= N;
    }

    static void initGarage(int[][] garage, int bottom, int left, int right) {
        for (int j = left; j <= right; j++) {  // 변경 필요한(bfs로 지운) 하는 좌~우에 대해 반복
            int pointer = pointers[j];  // 현재 열의 포인터(
            for (int si = bottom; si < N; si++) {  // bottom부터 N 이전 까지 반복
                int i = si;
                if (garage[i][j] == 0) {  // 빈 자리인 경우
                    boolean flag = true;
                    while (i < N) {
                        if (garage[i][j] != 0) {
                            garage[si][j] = garage[i][j];  // 블록을 아래로 이동
                            garage[i][j] = 0; // 이동한 블럭은 빈다
                            flag = false;
                            break;
                        }
                        i++;
                    }
                    if (flag) {  // 위에서 떨어지는 블록으로 채우기
                        garage[si][j] = map[pointer][j];  // originalMap에서 새로운 블록 가져오기
                        pointer++;
                    }
                }
            }
            pointers[j] = pointer;  // 포인터 업데이트
        }
    }


    static void copyPointers(int[] savedPointers, int left, int right) {
        for (int i = left; i <= right; i++) {
            pointers[i] = savedPointers[i];
        }
    }

    static void copyBox(int[][] nextGarage, int[][] garage, int bottom, int left, int right) {
        for (int j = left; j <= right; j++) {
            for (int i = bottom; i < N; i++) {
                nextGarage[i][j] = garage[i][j];
            }
        }
    }
}