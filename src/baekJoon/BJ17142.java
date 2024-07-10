package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ17142 {

    // 큐에서 사용할 바이러스의 이동 상태를 정의
    private static class State {
        int x, y, time;

        public State(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static int n, m;
    static List<int[]> viruses = new ArrayList<>();
    static int[][] map;
    static int initialEmptySpace = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int minTime = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken()); // 맵 가로세로 크기
        m = Integer.parseInt(stringTokenizer.nextToken()); // 초기 활성 바이러스 개수

        map = new int[n][n]; // 복사를 위한 원본 배열

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] == 0) {
                    initialEmptySpace++; // 빈칸 개수
                }
                if (map[i][j] == 2) {
                    viruses.add(new int[]{i, j}); // 바이러스 위치 저장
                }
            }
        }

        // 빈칸이 0개라면 즉시 종료
        if (initialEmptySpace == 0) {
            System.out.println(0);
            return;
        }


        simulate(0, 0, new int[m]);

        System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);

    }

    // depth -> 활성 바이러스 선택 개수
    // start -> activeVirusIdx를 선택하는 시작 인덱스
    // activeVirusIdx -> 선택된 활성 바이러스 인덱스
    private static void simulate(int depth, int start, int[] activeVirusIdx) {
        // 활성 바이러스를 m개 골랐다면
        if (depth == m) {
            // 바이러스 퍼뜨리기
            spreadVirus(activeVirusIdx);
            return;
        }

        // 활성 바이러스 고르기 start ~ (virus.size - 1)
        for (int i = start; i < viruses.size(); i++) {
            activeVirusIdx[depth] = i;
            simulate(depth + 1, i + 1, activeVirusIdx);
        }

    }

    private static void spreadVirus(int[] activeVirusIdx) {
        Queue<State> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];

        // 선택된 활성 바이러스를 큐에 삽입
        for (int idx : activeVirusIdx) {
            int virusX = viruses.get(idx)[0];
            int virusY = viruses.get(idx)[1];
            visited[virusX][virusY] = true;
            queue.offer(new State(virusX, virusY, 1));
        }

        int currMaxTime = 0; // 현재 BFS에 소모된 시간
        int visitCount = 0; // 현재 BFS에서 빈칸에 방문한 횟수

        // 큐가 비어있지 않고, 모든 빈칸에 방문하지 않았다면
        while (!queue.isEmpty() && visitCount != initialEmptySpace) {
            State curr = queue.poll();
            currMaxTime = Math.max(currMaxTime, curr.time);

            for (int d = 0; d < 4; d++) {
                int nextX = curr.x + dx[d];
                int nextY = curr.y + dy[d];

                if (isSafe(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] != 1) {
                    visited[nextX][nextY] = true;
                    queue.offer(new State(nextX, nextY, curr.time + 1));
                    if (map[nextX][nextY] == 0) visitCount++;
                }
            }
        }

        // 방문 횟수가 빈칸 개수와 같다면 정답(최소 시간)을 갱신
        if (visitCount == initialEmptySpace) {
            minTime = Math.min(minTime, currMaxTime);
        }

    }

    private static boolean isSafe(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

}
