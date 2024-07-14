package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17825 {

    private static class Horse {
        int idx, pos;
        int roadFlag = 0;
        boolean isFinish = false;

        Horse(int idx, int pos) {
            this.idx = idx;
            this.pos = pos;
        }
    }

    static int[] dice = new int[10];
    static int maxScore = 0;
    static int[] mainRoad, shortRoad10, shortRoad20, shortRoad30, shortRoad25;
    static int[][] roads;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        mainRoad = new int[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0};


        shortRoad10 =
                new int[]{10, 13, 16, 19, 25};

        shortRoad20 =
                new int[]{20, 22, 24, 25};

        shortRoad30 =
                new int[]{30, 28, 27, 26, 25};

        shortRoad25 =
                new int[]{25, 30, 35, 40, 0};

        roads = new int[][]{mainRoad, shortRoad10, shortRoad20, shortRoad30, shortRoad25};
        int[] horseNumbers = new int[10];
        simulate(0, horseNumbers);
        System.out.println(maxScore);


    }

    private static void simulate(int depth, int[] horseNumbers) {
        if (depth == 10) {
            int score = calcScore(horseNumbers);
            if (score > maxScore) maxScore = score;
            return;
        }

        for (int horse = 1; horse <= 4; horse++) {
            horseNumbers[depth] = horse;
            simulate(depth + 1, horseNumbers);
        }
    }

    private static int calcScore(int[] horseNumbers) {
        int score = 0;

        Horse[] horses = new Horse[5];
        boolean[] mainExist = new boolean[mainRoad.length];
        boolean[] short1Exist = new boolean[shortRoad10.length];
        boolean[] short2Exist = new boolean[shortRoad20.length];
        boolean[] short3Exist = new boolean[shortRoad30.length];
        boolean[] short4Exist = new boolean[shortRoad25.length];

        for (int i = 1; i <= 4; i++) {
            horses[i] = new Horse(i, 0);
        }

        for (int i = 0; i < 10; i++) {
            Horse selectedHorse = horses[horseNumbers[i]];
            boolean[] selectedExist =
                    selectedHorse.roadFlag == 0 ? mainExist :
                            selectedHorse.roadFlag == 1 ? short1Exist :
                                    selectedHorse.roadFlag == 2 ? short2Exist :
                                            selectedHorse.roadFlag == 3 ? short3Exist :
                                                    short4Exist;

            selectedExist[selectedHorse.pos] = false;

            if (selectedHorse.isFinish) return -1;

            int newPos = selectedHorse.pos + dice[i];
            if (selectedHorse.roadFlag == 0) {
                if (newPos == 5) {
                    selectedHorse.roadFlag = 1;
                    newPos = 0;
                } else if (newPos == 10) {
                    selectedHorse.roadFlag = 2;
                    newPos = 0;
                } else if (newPos == 15) {
                    selectedHorse.roadFlag = 3;
                    newPos = 0;
                } else if (newPos == 20) {
                    selectedHorse.roadFlag = 4;
                    newPos = 3;
                } else if (newPos > 20) {
                    selectedHorse.roadFlag = 4;
                    newPos -= 20;
                    newPos += 3;
                }
            }
            if (selectedHorse.roadFlag == 1) {
                if (newPos > 3) {
                    selectedHorse.roadFlag = 4;
                    newPos -= 4;
                }
            } else if (selectedHorse.roadFlag == 2) {
                if (newPos > 2) {
                    selectedHorse.roadFlag = 4;
                    newPos -= 3;
                }
            } else if (selectedHorse.roadFlag == 3) {
                if (newPos > 3) {
                    selectedHorse.roadFlag = 4;
                    newPos -= 4;
                }
            }
            if (selectedHorse.roadFlag == 4) {
                if (newPos > 3) {
                    selectedHorse.isFinish = true;
                    newPos = shortRoad25.length - 1;
                }
            }

            selectedExist =
                    selectedHorse.roadFlag == 0 ? mainExist :
                            selectedHorse.roadFlag == 1 ? short1Exist :
                                    selectedHorse.roadFlag == 2 ? short2Exist :
                                            selectedHorse.roadFlag == 3 ? short3Exist :
                                                    short4Exist;

            int[] selectedRoad =
                    selectedHorse.roadFlag == 0 ? mainRoad :
                            selectedHorse.roadFlag == 1 ? shortRoad10 :
                                    selectedHorse.roadFlag == 2 ? shortRoad20 :
                                            selectedHorse.roadFlag == 3 ? shortRoad30 :
                                                    shortRoad25;


            if (selectedExist[newPos]) return -1;
            if (!selectedHorse.isFinish) selectedExist[newPos] = true;
            selectedHorse.pos = newPos;

            score += selectedRoad[newPos];


        }

        return score;
    }
}
