package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;

class Node implements Comparable<Node> {
    int x, y, cost;

    public Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }
}

public class SW1249 {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = parseInt(bufferedReader.readLine());

        for (int i = 0; i < testCase; i++) {

            int n = parseInt(bufferedReader.readLine());
            int[][] depthMap = new int[n][n];
            int[][] timeMap = new int[n][n];
            int[] direction = {1, 2, 3, 4}; //"up", "down", "left", "right"

            for (int row = 0; row < n; row++) {
                depthMap[row] = Arrays.stream(bufferedReader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
                Arrays.fill(timeMap[row], Integer.MAX_VALUE);
            }

            timeMap[0][0] = 0;

            PriorityQueue<Node> queue = new PriorityQueue<>();
            queue.offer(new Node(0, 0, timeMap[0][0]));

            while (!queue.isEmpty()) {
                Node current = queue.poll();

                for (int d : direction) {
                    int nextX = current.x;
                    int nextY = current.y;
                    if (d == 1) {
                        nextY--;
                    } else if (d == 2) {
                        nextY++;
                    } else if (d == 3) {
                        nextX--;
                    } else {
                        nextX++;
                    }

                    if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n) {
                        if (timeMap[nextX][nextY] > depthMap[nextX][nextY] + timeMap[current.x][current.y]) {
                            timeMap[nextX][nextY] = depthMap[nextX][nextY] + timeMap[current.x][current.y];
                            queue.offer(new Node(nextX, nextY, timeMap[nextX][nextY]));
                        }
                    }
                }
            }

            System.out.println("#" + (i + 1) + " " + timeMap[n - 1][n - 1]);


        }
    }
}
