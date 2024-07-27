package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ14466 {

    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1}; // 상, 하, 좌, 우
    static int n;
    static int[][] roadInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int r = Integer.parseInt(stringTokenizer.nextToken());

        roadInfo = new int[n + 1][n + 1];
        int[][] cowChecker = new int[n + 1][n + 1];

        for (int i = 0; i < r; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int sr = Integer.parseInt(stringTokenizer.nextToken());
            int sc = Integer.parseInt(stringTokenizer.nextToken());
            int er = Integer.parseInt(stringTokenizer.nextToken());
            int ec = Integer.parseInt(stringTokenizer.nextToken());

            if (sr == er) {
                if (sc < ec) { // 오른쪽
                    roadInfo[sr][sc] |= 8; // 오른쪽 방향
                    roadInfo[er][ec] |= 4; // 왼쪽 방향
                } else { // 왼쪽
                    roadInfo[sr][sc] |= 4; // 왼쪽 방향
                    roadInfo[er][ec] |= 8; // 오른쪽 방향
                }
            } else if (sc == ec) {
                if (sr < er) { // 아래쪽
                    roadInfo[sr][sc] |= 2; // 아래쪽 방향
                    roadInfo[er][ec] |= 1; // 위쪽 방향
                } else { // 위쪽
                    roadInfo[sr][sc] |= 1; // 위쪽 방향
                    roadInfo[er][ec] |= 2; // 아래쪽 방향
                }
            }
        }

        for (int i = 0; i < k; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int row = Integer.parseInt(stringTokenizer.nextToken());
            int col = Integer.parseInt(stringTokenizer.nextToken());
            cowChecker[row][col] = i + 1;
        }

        boolean[][] visited = new boolean[n + 1][n + 1];
        List<List<Integer>> cowsIdxList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (visited[i][j]) continue;
                List<Integer> cowsIdx = new ArrayList<>();

                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[]{i, j});
                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    int[] curr = queue.poll();

                    if (cowChecker[curr[0]][curr[1]] != 0) {
                        cowsIdx.add(cowChecker[curr[0]][curr[1]]);
                    }

                    for (int d = 0; d < 4; d++) {
                        int nx = curr[0] + dx[d];
                        int ny = curr[1] + dy[d];

                        if (!isSafe(nx, ny)) continue;
                        if (visited[nx][ny]) continue;
                        if ((roadInfo[curr[0]][curr[1]] & (1 << d)) != 0) continue; // 이동 불가능한 방향 체크

                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }

                if (!cowsIdx.isEmpty()) {
                    cowsIdxList.add(cowsIdx);
                }
            }
        }

//        for (List<Integer> ci : cowsIdxList) {
//            for (int i : ci) {
//                System.out.print(i + " ");
//            }
//            System.out.println();
//        }

        // 각 구역의 소 수
        List<Integer> groupSizes = new ArrayList<>();
        for (List<Integer> ci : cowsIdxList) {
            groupSizes.add(ci.size());
        }

        // 전체 쌍의 수
        int totalPairs = (k * (k - 1)) / 2;

        // 같은 구역 내 쌍의 수
        int sameGroupPairs = 0;
        for (int size : groupSizes) {
            if (size > 1) {
                sameGroupPairs += (size * (size - 1)) / 2;
            }
        }

        // 구역이 다른 소들 간의 쌍의 수
        int differentGroupPairs = totalPairs - sameGroupPairs;

        System.out.println(differentGroupPairs);
    }

    private static boolean isSafe(int nx, int ny) {
        return 1 <= nx && nx <= n && 1 <= ny && ny <= n;
    }
}

// 2 3
// 1 4
// 5 6