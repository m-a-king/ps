package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17825 {

    private static class Horse {
        int idx, pos;
        int shortPassFlag = 0;
        boolean arrival = false;

        Horse(int idx, int pos) {
            this.idx = idx;
            this.pos = pos;
        }
    }

    static int[] dice;
    static int[] road, shortRoad10, shortRoad20, shortRoad30;
    static int maxScore = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        dice = new int[10];
        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        road = new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0};


        shortRoad10 =
                new int[]{0, 2, 4, 6, 8, 10, 13, 16, 19, 25, 30, 35, 40, 0};
        //      new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0};

        shortRoad20 =
                new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 25, 30, 35, 40, 0};
        //      new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0};

        shortRoad30 =
                new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 28, 27, 26, 25, 30, 35, 40, 0};
        //      new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0};


        simulate(0, new int[10]);
        System.out.println(maxScore); // 최대 점수 출력

    }

    private static void simulate(int depth, int[] horses) {
        if (depth == 10) {
            int score = calcScore(horses);
            if (score > maxScore) { // 충돌이 발생하지 않은 경우만 최대 점수를 갱신
                maxScore = score;
            }
            return;
        }

        for (int horseIdx = 1; horseIdx <= 4; horseIdx++) {
            horses[depth] = horseIdx;
            simulate(depth + 1, horses);
        }
    }
    // d 0 1 2 3 4 5 6 7 8 9
    // w 5 1 2 3 4 5 5 3 2 4
    // h 1 1 1 1 1 1 1 1 1 1 -> s
    // h 1 1 1 1 1 1 1 1 1 2
    // h 1 1 1 1 1 1 1 1 1 3
    // h 1 1 1 1 1 1 1 1 1 4
    // h 1 1 1 1 1 1 1 1 2 1 -> s
    // h 1 1 1 1 1 1 1 1 2 2 -> s
    // h 1 1 1 1 1 1 1 1 2 3 -> s


    private static int calcScore(int[] horses) {
        int score = 0;
        Horse[] horsesState = new Horse[5]; // 1 ~ 4
        boolean[] exist = new boolean[road.length];
        boolean[] exist1 = new boolean[shortRoad10.length];
        boolean[] exist2 = new boolean[shortRoad20.length];
        boolean[] exist3 = new boolean[shortRoad30.length];
        boolean[][] existences = {exist, exist1, exist2, exist3}; // 배열을 관리하는 배열


        for (int i = 1; i <= 4; i++) {
            horsesState[i] = new Horse(i, 0);
        }

        for (int i = 0; i < 10; i++) {
            Horse curr = horsesState[horses[i]];
            if (curr.arrival) return -1; // 도착한 말을 움직이려하면 종료

            int[] selectedRoad = road;
            boolean[] selectedExist = existences[curr.shortPassFlag];

            if (curr.shortPassFlag > 0) {
                if (curr.shortPassFlag == 1) selectedRoad = shortRoad10;
                if (curr.shortPassFlag == 2) selectedRoad = shortRoad20;
                if (curr.shortPassFlag == 3) selectedRoad = shortRoad30;
            }

            int newPos = curr.pos + dice[i];
            if (newPos >= selectedRoad.length) {
                newPos = selectedRoad.length - 1;
                curr.arrival = true;
            }

            // 여기서 현재 위치 업데이트 전에 위치를 비우고
            selectedExist[curr.pos] = false; // 현재 위치를 비움
            if (selectedRoad[curr.pos] == 10) {
                existences[0][5] = false;
                existences[1][5] = false;
//                existences[2][5] = false;
//                existences[3][5] = false;
            }
            if (selectedRoad[curr.pos] == 20) {
                existences[0][10] = false;
//                existences[1][10] = false;
                existences[2][10] = false;
//                existences[3][10] = false;
            }
            if (selectedRoad[curr.pos] == 30) {
                existences[0][15] = false;
//                existences[1][15] = false;
//                existences[2][15] = false;
                existences[3][15] = false;
            }

            curr.pos = newPos;

            if (selectedExist[curr.pos]) return -1; // 충돌 발생

            if (selectedRoad[curr.pos] != 0) selectedExist[curr.pos] = true; // 새 위치를 차지
            if (selectedRoad[curr.pos] == 40) {
                existences[0][existences[0].length - 2] = true;
                existences[1][existences[1].length - 2] = true;
                existences[2][existences[2].length - 2] = true;
                existences[3][existences[3].length - 2] = true;
            }
            if (selectedRoad[curr.pos] == 35) {
                existences[1][existences[1].length - 3] = true;
                existences[2][existences[2].length - 3] = true;
                existences[3][existences[3].length - 3] = true;
            }
            if (selectedRoad[curr.pos] == 30) {
                existences[1][existences[1].length - 4] = true;
                existences[2][existences[2].length - 4] = true;
                existences[3][existences[3].length - 4] = true;
            }
            if (selectedRoad[curr.pos] == 25) {
                existences[1][existences[1].length - 5] = true;
                existences[2][existences[2].length - 5] = true;
                existences[3][existences[3].length - 5] = true;
            }
            if (curr.shortPassFlag == 0) {
                if (selectedRoad[curr.pos] == 10) {
                    curr.shortPassFlag = 1;
                    exist1[curr.pos] = true;
                }
                if (selectedRoad[curr.pos] == 20) {
                    curr.shortPassFlag = 2;

                    exist2[curr.pos] = true;
                }
                if (selectedRoad[curr.pos] == 30) {
                    curr.shortPassFlag = 3;
                    exist3[curr.pos] = true;
                }
            }

            score += selectedRoad[curr.pos];
        }
        if (score == 218) {  // 특정 점수에 대한 상세 로그 출력
            System.out.println("Score is 218 for this configuration:");
            for (int i = 1; i <= 4; i++) {
                Horse horse = horsesState[i];
                System.out.println("Horse " + horse.idx + ": Position = " + horse.pos + ", ShortPassFlag = " + horse.shortPassFlag + ", Arrival = " + horse.arrival);
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("Move " + (i+1) + ": Horse " + horses[i] + " rolls " + dice[i] + " to position " + horsesState[horses[i]].pos); // 현재 이동하는 말과 주사위 값, 그리고 그 결과 위치 출력
            }
        }
        return score;
    }


}

// 1
// 2 0 40
// 3 0 4
// 4 1 40