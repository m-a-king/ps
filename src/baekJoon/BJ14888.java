package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ14888 {

    private static class State {
        int[] availableOperator;
        int[] selectedOperator;
        int idx;

        // 기존 생성자
        public State(int n, int[] availableOperator) {
            this.availableOperator = Arrays.copyOf(availableOperator, availableOperator.length); // 배열 복사
            this.selectedOperator = new int[n - 1];
            this.idx = 0;
        }

        // 복사 생성자
        public State(State other) {
            this.availableOperator = Arrays.copyOf(other.availableOperator, other.availableOperator.length);
            this.selectedOperator = Arrays.copyOf(other.selectedOperator, other.selectedOperator.length);
            this.idx = other.idx;
        }

    }

    static int n;
    static int[] numbers;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        numbers = new int[n];
        int[] operators = new int[4];

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                State initialState = new State(n, operators);
                initialState.availableOperator[i]--;
                initialState.selectedOperator[initialState.idx++] = i;
                bfs(initialState);
            }
        }

        System.out.println(max);
        System.out.println(min);

    }

    private static void bfs(State state) {
        Queue<State> queue = new ArrayDeque<>();
        queue.offer(state);

        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (current.idx == n - 1) {
                calc(current);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                if (current.availableOperator[i] > 0) {
                    State next = new State(current); // current의 복사본을 생성
                    next.availableOperator[i]--; // 선택한 연산자 사용
                    next.selectedOperator[next.idx++] = i; // 연산자 선택 기록

                    queue.offer(next);
                }
            }
        }
    }

    private static void calc(State current) {
        int res = numbers[0];
        for (int i = 0; i < n - 1; i++) {
            switch (current.selectedOperator[i]) {
                case 0:
                    res += numbers[i + 1];
                    break;
                case 1:
                    res -= numbers[i + 1];
                    break;
                case 2:
                    res *= numbers[i + 1];
                    break;
                case 3:
                    res /= numbers[i + 1];
                    break;
            }
        }

        min = Math.min(min, res);
        max = Math.max(max, res);
    }
}
