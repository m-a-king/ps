package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ13141_2 {

    private static class Edge implements Comparable<Edge> {
        int to;
        double length;
        double speed;  // 속도 필드 추가

        public Edge(int to, double length, double speed) {
            this.to = to;
            this.length = length;
            this.speed = speed;
        }

        // 두 번째 생성자: 기본적으로 속도 1로 초기화
        public Edge(int to, double length) {
            this(to, length, 1.0);
        }

        @Override
        public int compareTo(Edge o) {
            int lengthComparison = Double.compare(this.length / this.speed, o.length / o.speed);

            if (lengthComparison == 0) {
                return Double.compare(this.speed, o.speed);
            }

            return lengthComparison;
        }
    }

    static final int MAX = 20_000_001;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        int[][] minDist = new int[n + 1][n + 1];
        int[][] maxDist = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(minDist[i], MAX);
            minDist[i][i] = 0;
            // maxDist 는 자동
        }

        // 입력 처리
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            int length = Integer.parseInt(stringTokenizer.nextToken());

            minDist[from][to] = Math.min(minDist[from][to], length);
            minDist[to][from] = minDist[from][to];

            maxDist[from][to] = Math.max(maxDist[from][to], length);
            maxDist[to][from] = maxDist[from][to];
        }

        for (int i = 1; i < minDist.length; i++) {
            for (int j = 1; j < minDist.length; j++) {
                System.out.print(minDist[i][j] > 30 ? "X" : minDist[i][j]>20 ? minDist[i][j] : "  "+ minDist[i][j]);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("최소 계산 전");

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    minDist[i][j] = Math.min(minDist[i][j], minDist[i][k] + minDist[k][j]);
                }
            }
        }
        System.out.println();
        System.out.println("최소 계산 후");

        for (int i = 1; i < minDist.length; i++) {
            for (int j = 1; j < minDist.length; j++) {
                System.out.print(minDist[i][j] > 30 ? "X" : minDist[i][j]>20 ? minDist[i][j] : "  "+ minDist[i][j]);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("최대");

        for (int i = 1; i < minDist.length; i++) {
            for (int j = 1; j < minDist.length; j++) {
                System.out.print(maxDist[i][j] > 30 ? "X" : maxDist[i][j]>20 ? maxDist[i][j] : "  "+ maxDist[i][j]);
            }
            System.out.println();
        }


        System.out.println();


        double minTime = Double.MAX_VALUE;

        for (int start = 1; start <= n; start++) {

            double maxRemainTime = 0;

            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    if (minDist[from][to] >= MAX) continue;

                    double remainLength = maxDist[from][to] - (minDist[start][to] - minDist[start][from]);
                    double currRemainTime = remainLength / 2 + minDist[start][to];

                    if (remainLength < 0) continue;

                    maxRemainTime = Math.max(maxRemainTime, currRemainTime);
                }
            }

            minTime = Math.min(minTime, maxRemainTime);
        }

        System.out.println(minTime);


    }
}
