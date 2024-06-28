package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ24465 {

    static int[][] starGari = new int[13][2];

    public static void main(String[] args) throws IOException {

        starGari[1] = new int[]{1, 20};
        starGari[2] = new int[]{2, 19};
        starGari[3] = new int[]{3, 21};
        starGari[4] = new int[]{4, 20};
        starGari[5] = new int[]{5, 21};
        starGari[6] = new int[]{6, 22};
        starGari[7] = new int[]{7, 23};
        starGari[8] = new int[]{8, 23};
        starGari[9] = new int[]{9, 23};
        starGari[10] = new int[]{10, 23};
        starGari[11] = new int[]{11, 23};
        starGari[12] = new int[]{12, 22};

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        boolean[] visited = new boolean[13];

        for (int i = 0; i < 7; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int alohaMonth = Integer.parseInt(stringTokenizer.nextToken());
            int alohaDay = Integer.parseInt(stringTokenizer.nextToken());

            if (alohaMonth == 1) {
                if (alohaDay <= 19) {
                    visited[12] = true;  // 1월 19일까지는 염소자리
                } else {
                    visited[1] = true;
                }
            } else if (alohaMonth == 12) {
                if (alohaDay >= 22) {
                    visited[12] = true;
                } else {
                    visited[11] = true;
                }
            } else {
                if (starGari[alohaMonth][1] <= alohaDay) {
                    visited[alohaMonth] = true;
                } else {
                    visited[alohaMonth - 1] = true;
                }
            }
        }

        int n = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        List<int[]> answerList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int newMonth = Integer.parseInt(stringTokenizer.nextToken());
            int newDay = Integer.parseInt(stringTokenizer.nextToken());

            if (newMonth == 1) {
                if (newDay <= 19 && !visited[12]) {
                    answerList.add(new int[]{newMonth, newDay});
                } else if (newDay > 19 && !visited[1]) {
                    answerList.add(new int[]{newMonth, newDay});
                }
            } else if (newMonth == 12) {
                if (newDay >= 22 && !visited[12]) {
                    answerList.add(new int[]{newMonth, newDay});
                } else if (newDay < 22 && !visited[11]) {
                    answerList.add(new int[]{newMonth, newDay});
                }
            } else {
                if (starGari[newMonth][1] <= newDay && !visited[newMonth]) {
                    answerList.add(new int[]{newMonth, newDay});
                } else if (starGari[newMonth][1] > newDay && !visited[newMonth - 1]) {
                    answerList.add(new int[]{newMonth, newDay});
                }
            }
        }

        if (answerList.isEmpty()) {
            System.out.print("ALL FAILED");
            return;
        }

        answerList.sort((a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        for (int[] md : answerList) {
            stringBuilder.append(md[0]).append(" ").append(md[1]).append("\n");
        }

        System.out.print(stringBuilder.toString().trim());
    }
}