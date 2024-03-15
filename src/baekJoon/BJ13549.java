package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ13549 {

    private static class Subin {
        int pos, time;

        public Subin(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }

    }

    // 수빈이 (start)
    static int s;

    // 동생 (end)
    static int e;

    // 재방문 확인 배열
    static boolean[] visited;

    // 수빈이의 다음 위치를 저장할 배열
    // 성능 최적화를 위해서 스태틱으로 선언
    // 사실상 메인함수의 while문 밖에 적어도 무방하다고 생각
    static int[] nextPos = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        s = Integer.parseInt(stringTokenizer.nextToken());
        e = Integer.parseInt(stringTokenizer.nextToken());

        // 수빈이가 동생보다 앞에 있는 경우
        if (s > e) {
            System.out.println(s - e);
            return;
        }

        // 수빈이와 동생이 동일한 위치에 있는 경우
        else if (s == e) {
            System.out.println(0);
            return;
        }

        // !! Queue가 아닌 Deque로 선언해야 함 !!
        // 수빈 객체를 앞뒤로 offer(add) 해야 하기 때문이다 (0-1 bfs)
        // 혹은 priorityQueue (다익스트라)
        Deque<Subin> deque = new ArrayDeque<>();

        // 배열의 크기로 e + 2가 최적의 수인지 확신할 수 없지만
        // 100_001과 같은 상수로 표현하고 싶지 않았음
        // 2를 더하는 것도 신경 쓰이긴 함
        // 참고: e+2와 100_001을 백준을 통해서 비교해보니 전자가 10% 내외로 효율적임
        visited = new boolean[e + 2];

        deque.offer(new Subin(s, 0));
        visited[s] = true;

        while (!deque.isEmpty()) {
            Subin current = deque.poll();

            // 종료 조건
            if (current.pos == e) {
                System.out.println(current.time);
                return;
            }

            nextPos[0] = current.pos * 2;
            nextPos[1] = current.pos - 1;
            nextPos[2] = current.pos + 1;

            for (int i = 0; i < 3; i++) {

                // 다음 위치로 움직일 수 있다면
                if (canMove(nextPos[i])) {
                    visited[nextPos[i]] = true;

                    // 순간이동의 경우는 시간이 추가되지 않고
                    // 덱(deque)의 앞으로 삽입함
                    if (i == 0) {
                        deque.offerFirst(new Subin(nextPos[i], current.time));
                    }

                    // 걸어서 이동하는 경우는 시간이 추가되며
                    // 덱의 끝으로 삽입함
                    else {
                        deque.offerLast(new Subin(nextPos[i], current.time + 1));
                    }
                }
            }

        }
    }

    // 수빈이가 이동할 위치가 올바른지 확인하며, 재방문도 확인함
    private static boolean canMove(int pos) {
        return 0 <= pos && pos < e + 2 && !visited[pos];
    }
}