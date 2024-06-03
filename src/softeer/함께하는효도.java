package softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 함께하는효도 {

    private static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean[][] visited;
    static int score = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    // 위치(x,y)를 4개씩 묶는 리스트 (path = List<Pos>)
    // 그 리스트들을 시작지점에 알맞게 묶는 리스트 (List<path>)
    static List<List<Pos>> dfsList = new ArrayList<>();
    static List<Integer> weights = new ArrayList<>();
    static int[][] map;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[n][n];
        List<Pos> friends = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            friends.add(new Pos(x - 1, y - 1)); // -1해서 인덱스 맞추기
        }

        for (int i = 0; i < m; i++) {
            Pos start = friends.remove(friends.size() - 1);
            visited = new boolean[n][n]; // 각 친구마다 방문 배열 초기화
            visited[start.x][start.y] = true;
            dfs(start, new ArrayList<>(), 1, map[start.x][start.y]);
        }

        // 지금까지 계산된 각 친구들의 방문들을 모두 조합해보기
        findMaxScore(0, 0, new boolean[dfsList.size()]);
        System.out.println(score);
    }



    private static void dfs(Pos now, List<Pos> path, int depth, int weight) {
        path.add(now);

        if (depth == 4) {
            dfsList.add(new ArrayList<>(path));
            path.remove(path.size() - 1); // 현재 노드를 경로에서 제거
            weights.add(weight);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nextX = now.x + dx[d];
            int nextY = now.y + dy[d];

            if (!isSafe(nextX, nextY)) continue;
            if (visited[nextX][nextY]) continue;

            visited[nextX][nextY] = true;
            dfs(new Pos(nextX, nextY), path, depth + 1, weight + map[nextX][nextY]);
            visited[nextX][nextY] = false;
        }

        path.remove(path.size() - 1); // 현재 노드를 경로에서 제거

    }

    // 두 경로가 겹치는지 체크
    private static boolean isOverlap(List<Pos> path1, List<Pos> path2) {
        for (Pos pos1 : path1) {
            for (Pos pos2 : path2) {
                if (pos1.x == pos2.x && pos1.y == pos2.y) {
                    return true;
                }
            }
        }
        return false;
    }

    // 경로들의 합 최대값 찾기
    private static void findMaxScore(int idx, int currentScore, boolean[] used) {
        if (idx == dfsList.size()) {
            score = Math.max(score, currentScore);
            return;
        }

        // 현재 경로를 사용하지 않는 경우
        findMaxScore(idx + 1, currentScore, used);

        // 현재 경로를 사용하는 경우
        boolean canUse = true;
        for (int i = 0; i < idx; i++) {
            // 아직 선택되지 않았어야함, 또한 그와 현재 경로가 겹치면 안됨
            if (used[i] && isOverlap(dfsList.get(i), dfsList.get(idx))) {
                canUse = false;
                break;
            }
        }

        if (canUse) {
            used[idx] = true;
            findMaxScore(idx + 1, currentScore + weights.get(idx), used);
            used[idx] = false;
        }
    }

    private static boolean isSafe(int nextX, int nextY) {
        return 0 <= nextX && nextX < n && 0 <= nextY && nextY < n;
    }
}


