package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ23887 {

    private static class Student implements Comparable<Student> {
        int idx, row, col, turn;

        public Student(int idx, int row, int col, int turn) {
            this.idx = idx;
            this.row = row;
            this.col = col;
            this.turn = turn;
        }

        @Override
        public int compareTo(Student o) {
            if (this.turn == o.turn) return this.idx - o.idx;
            return this.turn - o.turn;
        }
    }

    static int n, m, k, s;
    static int[][] map;
    static List<Student> students;
    static List<List<Integer>> adjList = new ArrayList<>();

    static int count = 1;

    static int[] needPaper;


    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());

        for (int i = 0; i <= k; i++) {
            adjList.add(new ArrayList<>());
        }

        needPaper = new int[k + 1];

        map = new int[n + 1][m + 1];

        students = new ArrayList<>();

        Student temp = new Student(-1, -1, -1, -1);
        students.add(temp); // 0번 사용안함

        for (int i = 1; i <= k; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int row = Integer.parseInt(stringTokenizer.nextToken());
            int col = Integer.parseInt(stringTokenizer.nextToken());
            map[row][col] = i;
            students.add(new Student(i, row, col, 0));
        }

        s = Integer.parseInt(bufferedReader.readLine());
        bfs(s);

        if (count != k) {
            System.out.println(-1);
            return;
        }

        dfs(s);

        needPaper[s] = k;

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < needPaper.length; i++) {
            stringBuilder.append(needPaper[i]).append(" ");
        }

        System.out.print(stringBuilder.toString().trim());
    }

    private static void dfs(int target) {
        needPaper[target] = 1;

        for (int adj : adjList.get(target)) {
            dfs(adj);
            needPaper[target] += needPaper[adj];
        }
    }


    private static void bfs(int start) {
        PriorityQueue<Student> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[n + 1][m + 1];

        Student startStudent = students.get(start);
        pq.offer(startStudent);
        visited[startStudent.row][startStudent.col] = true;

        while (!pq.isEmpty()) {
            Student curr = pq.poll();
            int tempCount = count;

            for (int d = 0; d < 8; d++) {
                int nextRow = curr.row + dx[d];
                int nextCol = curr.col + dy[d];

                if (!isSafe(nextRow, nextCol)) continue;
                if (visited[nextRow][nextCol]) continue;
                visited[nextRow][nextCol] = true;
                if (!isStudent(nextRow, nextCol)) continue;

                int nextStudentIdx = map[nextRow][nextCol];
                adjList.get(curr.idx).add(nextStudentIdx);
                Student nextStudent = students.get(nextStudentIdx);
                nextStudent.turn = curr.turn + 1;
                pq.offer(nextStudent);
                count++;
            }

            int currCount = count - tempCount;
            if (currCount == 0) {
                needPaper[curr.idx] = 1;
            }
        }
    }


    private static boolean isStudent(int row, int col) {
        return map[row][col] > 0;
    }

    private static boolean isSafe(int row, int col) {
        return 1 <= row && row <= n && 1 <= col && col <= m;
    }
}
