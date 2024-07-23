package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ2457 {

    private static class Flower {
        int startM, startD, endM, endD;

        public Flower(int startM, int startD, int endM, int endD) {
            this.startM = startM;
            this.startD = startD;
            this.endM = endM;
            this.endD = endD;
        }

//        @Override
//        public String toString() {
//            return "Flower{" +
//                    "startM=" + startM +
//                    ", startD=" + startD +
//                    ", endM=" + endM +
//                    ", endD=" + endD +
//                    '}';
//        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        List<Flower> flowers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int startM = Integer.parseInt(stringTokenizer.nextToken());
            int startD = Integer.parseInt(stringTokenizer.nextToken());
            int endM = Integer.parseInt(stringTokenizer.nextToken());
            int endD = Integer.parseInt(stringTokenizer.nextToken());

            // 꽃은 모두 같은 해에 피어서 같은 해에 진다
            if (startM < 3) {
                startM = 3;
                startD = 1;
            }

            if (endM == 12) {
                endD = 1;
            }

            flowers.add(new Flower(startM, startD, endM, endD));
        }

        flowers.sort((a, b) -> {
            if (a.startM != b.startM) return a.startM - b.startM;
            if (a.startD != b.startD) return a.startD - b.startD;
            if (a.endM != b.endM) return b.endM - a.endM;
            return b.endD - a.endD;
        });

//        // 정렬된 꽃 리스트 출력
//        System.out.println("정렬된 꽃 리스트:");
//        for (Flower flower : flowers) {
//            System.out.println(flower);
//        }


        Flower lastFlower = flowers.get(0); // 선택된 꽃 (초기 값 = 정렬 후, 첫 꽃)
        if (lastFlower.startM == 3 && lastFlower.startD > 1 || lastFlower.startM > 3) {
            System.out.println(0);
            return;
        }

        int count = 1;
        int bestIdx = 0;
        int bestEM = 0;
        int bestED = 0;

        for (int i = 1; i < flowers.size(); i++) {
//            System.out.println();

            int tempIdx = i;
            boolean found = false;
            if (lastFlower.endM == 12) {
                break;
            }

            while (tempIdx < flowers.size()) {
                Flower currFlower = flowers.get(tempIdx);
                int currEM = currFlower.endM;
                int currED = currFlower.endD;
//                System.out.print("currFlower = " + currFlower.toString() + " ");

                // 이전에 선택된 꽃이 지기전에 이번 꽃이 필 수 있다면 ?
                // 다음 꽃 검사하며 가장 늦게 지는 꽃을 찾음
                if (lastFlower.endM > currFlower.startM || (lastFlower.endM == currFlower.startM && lastFlower.endD >= currFlower.startD)) {
//                    System.out.println("이 꽃은 가능합니다. 다음 꽃 검사");
                    if (isBest(bestEM, currEM, bestED, currED)) {
                        bestIdx = tempIdx;
                        bestEM = currEM;
                        bestED = currED;
                    }
                    tempIdx++;
                    found = true;
                    continue;
                }

                if (tempIdx == i) {
                    System.out.println(0);
                    return;
                }

//                System.out.println("이 꽃은 불가능합니다. 이전 꽃 검사");
                lastFlower = flowers.get(bestIdx);
//                System.out.print("bestFlower = " + lastFlower.toString() + " ");
//                System.out.println("이전 꽃들 중 최고의 꽃을 고릅니다.");
                i = tempIdx - 1;
                count++;
                break;
            }

            // 전부 가능한 경우 마지막으로 선택된 꽃 업데이트
            if (found && tempIdx == flowers.size()) {
                lastFlower = flowers.get(bestIdx);
                count++;
                break;
            }

        }

        System.out.println(lastFlower.endM == 12 ? count : 0);
    }

    private static boolean isBest(int bestEM, int currEM, int bestED, int currED) {
        return bestEM < currEM || (bestEM == currEM && bestED < currED);
    }

}

