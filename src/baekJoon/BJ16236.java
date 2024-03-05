package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ16236 {

    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 0, 1}; // 상, 좌, 우, 하
    static int[] dy = {0, -1, 1, 0};

    private static class Shark {
        int x, y, size = 2, eat = 0;
        int count;

        public Shark(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }


    private static class Fish implements Comparable<Fish> {
        int x, y;
        int cost;

        public Fish(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.cost < o.cost) {
                return -1; // this의 cost가 더 작으면 음수 반환
            } else if (this.cost > o.cost) {
                return 1; // this의 cost가 더 크면 양수 반환
            } else {
                // cost가 같을 때 x 좌표로 비교
                if (this.x < o.x) {
                    return -1; // this의 x가 더 작으면 음수 반환
                } else if (this.x > o.x) {
                    return 1; // this의 x가 더 크면 양수 반환
                } else {
                    // x 좌표도 같을 때 y 좌표로 비교
                    if (this.y < o.y) {
                        return -1; // this의 y가 더 작으면 음수 반환
                    } else if (this.y > o.y) {
                        return 1; // this의 y가 더 크면 양수 반환
                    } else {
                        return 0; // 모든 필드가 같으면 0 반환
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());

        map = new int[n][n];

        Shark shark = null;

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] == 9) {
                    shark = new Shark(i, j, 0);
                    map[i][j] = 0; // 상어 위치 초기화
                }
            }
        }

        System.out.println(simulate(shark));

    }

    private static int simulate(Shark shark) {
        int time = 0;

        while (true) {
            Queue<Shark> queue = new ArrayDeque<>();
            PriorityQueue<Fish> fishes = new PriorityQueue<>();
            visited = new boolean[n][n];

            queue.offer(shark);
            visited[shark.x][shark.y] = true;

            while (!queue.isEmpty()) {
                Shark current = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nextX = current.x + dx[i];
                    int nextY = current.y + dy[i];

                    if (canMove(nextX, nextY, shark.size)) {
                        visited[nextX][nextY] = true;
                        queue.offer(new Shark(nextX, nextY, current.count + 1));

                        if (canEat(nextX, nextY, shark.size)) {
                            fishes.offer(new Fish(nextX, nextY, current.count + 1));
                        }
                    }
                }
            }

            if (fishes.isEmpty()) {
                break;
            }

            Fish fish = fishes.poll();
            time += fish.cost;
            shark.x = fish.x;
            shark.y = fish.y;
            shark.count = 0;
            shark.eat++;
            map[fish.x][fish.y] = 0;
            if (shark.eat == shark.size) {
                shark.size++;
                shark.eat = 0;
            }
        }

        return time;
    }

    private static boolean canEat(int x, int y, int size) {
        return map[x][y] != 0 && map[x][y] < size;
    }

    private static boolean canMove(int x, int y, int size) {
        return 0 <= x && x < n && 0 <= y && y < n && !visited[x][y] && map[x][y] <= size;

    }
}
