package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1981 {

    // 입력된 map 탐색을 위한 위치를 저장하는 클래스
    private static class Pos {
        int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n;
    static int[][] map;
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());

        map = new int[n][n];
        visited = new boolean[n][n];

        // 입력 중 최댓값, 최솟값를 저장할 변수 초기화
        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());

                // 최댓값 찾기
                if (map[i][j] > max) {
                    max = map[i][j];
                }
                // 최솟값 찾기
                if (map[i][j] < min) {
                    min = map[i][j];
                }
            }
        }

        /*********************************************
         *  int left = min
         *  int right = max
         *  위와 같이 초기화하면 해결할 수 없는 탐색 범위가 있음
         *  자세한 설명은 아래에 기재함
         *********************************************/
        int left = 0;
        int right = max - min;
        int mid;

        int startValue = map[0][0];
        int targetValue = map[n - 1][n - 1];
        int minGap = Math.abs(startValue - targetValue);

        int answer = Integer.MAX_VALUE;

        // 이진 탐색(이분 탐색) 시작
        while (left <= right) {
            mid = (left + right) / 2;

            // bfs 성공, 실패를 저장하는 변수
            boolean bfsSuccessCheck = false;

            /*****************************************************************************
             *  입력 값 중 최솟 값 -> min
             *  입력 값 중 최댓 값 -> max
             *  현재 mid 값으로 bfs 탐색이 가능한 범위를 찾음
             *  즉, bfs 탐색 범위의 시작을 찾음
             *  시작 지점(i)은 min ~ (max - mid)의 범위에서 가능함
             *  (max - mid)까지 가능한 이유는 입력 값 중에서 max를 넘어선 수가 없기 때문임
             *  풀어서 말하자면, (max - mid) ~ max 가 가장 마지막으로 올바른(효율적인) 탐색이 가능한 범위
             *****************************************************************************/
            for (int i = min; i <= max - mid; i++) {

                // 시작지점과 목표지점은 반드시 탐색 범위안에 포함되어야 함
                if (i <= startValue && startValue <= i + mid && i <= targetValue && targetValue <= i + mid) {

                    /*******************************************************
                     *  시작지점(0,0)과 목표지점(n-1,n-1)은 반드시 거쳐야 함
                     *  시작지점 값과 목표지점 값의 차이 -> minGap
                     *  거쳐간 수들 중 최소한의 차이, 즉 문제에서 요구하는 값 -> answer
                     *  answer >= minGap 을 만족해야 함.
                     *  answer = 탐색이 가능한 mid 중에서 가장 작은 mid
                     *  따라서, mid < minGap 은 절대로 bfs 탐색이 불가능
                     *  bfsSuccessCheck = false, 반복 break
                     *******************************************************/
                    if (mid < minGap) {
                        break;
                    }

                    // 현재 mid 값으로 bfs를 시작지점부터 목표지점까지 가능한지 저장
                    bfsSuccessCheck = bfs(i, i + mid);

                    // 가능했다면 현재 mid는 answer가 될 수 있음
                    // 더 이상 현재 mid로 탐색 할 필요 없음 -> 반복 break
                    if (bfsSuccessCheck) {
                        break;
                    }
                }
            }

            /*******************************************************
             *  해당 mid로 탐색이 가능했다면?
             *  answer 갱신 해두기
             *  mid 감소 도전(더 작은 mid로 탐색이 가능한지 알아보기 위해서)
             *  -> right = mid - 1
             *******************************************************/
            if (bfsSuccessCheck) {
                right = mid - 1;
                answer = Math.min(answer, mid);
            }

            /*******************************************************
             *  // 해당 mid로 탐색이 불가능했다면?
             *  mid 증가 필요
             *  -> left = mid + 1
             *******************************************************/
            else {
                left = mid + 1;
            }
        }

        // (left > right) -> while 문 탈출
        // 탐색 끝, 결과 출력
        System.out.println(answer);


    }

    private static boolean bfs(int rangeStartPoint, int rangeEndPoint) {
        // 각 bfs 탐색마다 visited 배열 초기화
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
        }

        Queue<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Pos current = queue.poll();

            // 반복문 탈출 조건 (목표 지점 도착했다면?)
            if (current.row == n - 1 && current.col == n - 1) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = current.row + dx[i];
                int nextCol = current.col + dy[i];

                if (isSafe(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                    int nextVal = map[nextRow][nextCol];

                    if (rangeStartPoint <= nextVal && nextVal <= rangeEndPoint) {
                        queue.offer(new Pos(nextRow, nextCol));
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }

        // 해당 범위로는 목표 지점 도착 불가능했다면?
        return false;
    }

    // 올바른 범위인지 검사
    private static boolean isSafe(int nextRow, int nextCol) {
        return 0 <= nextRow && nextRow < n && 0 <= nextCol && nextCol < n;
    }
}
//
//8
//1 1 1 3 1 1 1 2
//3 3 1 3 1 2 1 2
//1 1 1 1 1 2 1 2
//3 3 3 3 3 3 1 2
//1 1 1 3 3 2 1 2
//1 2 1 1 1 1 1 2
//1 3 3 3 3 2 2 2
//1 1 1 1 1 1 1 1