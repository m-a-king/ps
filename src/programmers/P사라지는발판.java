package programmers;

public class P사라지는발판 {

    public static void main(String[] args) {
        int[][] board = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[] aloc = {1, 0};
        int[] bloc = {1, 2};

        System.out.println(solution(board, aloc, bloc));
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;

    public static int solution(int[][] board, int[] aloc, int[] bloc) {
        map = board;
        return simulate(aloc, bloc);
    }

    private static int simulate(int[] me, int[] you) {
        // 난 이미 죽어있다. 단지 아직 깨닫지 못했을 뿐
        if (map[me[0]][me[1]] == 0) return 0;

        // 이긴다면 최소
        // 진다면 최대
        // 짝수면 패배하는 중
        // 홀수면 승리하는 중
        int best = 0;

        for (int d = 0; d < 4; d++) {
            int[] next = {me[0] + dx[d], me[1] + dy[d]};

            if (isWall(next)) continue;
            if (map[next[0]][next[1]] == 0) continue;

            map[me[0]][me[1]]--;
            int res = simulate(you, next) + 1;
            map[me[0]][me[1]]++;

            // 1. 현재 저장된 턴은 패배인데 새로 계산된 턴은 승리인 경우
            if(best % 2 == 0 && res % 2 == 1) best = res; // 바로 갱신
                // 2. 현재 저장된 턴과 새로 계산된 턴이 모두 패배인 경우
            else if(best % 2 == 0 && res % 2 == 0) best = Math.max(best, res); // 최대한 늦게 지는걸 선택
                // 3. 현재 저장된 턴과 새로 계산된 턴이 모두 승리인 경우
            else if(best % 2 == 1 && res % 2 == 1) best = Math.min(best, res); // 최대한 빨리 이기는걸 선택

        }

        return best;
    }


    private static boolean isWall(int[] next) {
        int row = next[0];
        int col = next[1];
        return row < 0 || row >= map.length || col < 0 || col >= map[0].length;
    }
}