package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ31848 {

    private static class Dotori implements Comparable<Dotori> {
        int seq, size;

        public Dotori(int seq, int size) {
            this.seq = seq;
            this.size = size;
        }

        @Override
        public int compareTo(Dotori o) {
            return this.size - o.size;
        }
    }

    private static class DotoriHole implements Comparable<DotoriHole> {
        int seq, hole;

        public DotoriHole(int seq, int hole) {
            this.seq = seq;
            this.hole = hole;
        }

        @Override
        public int compareTo(DotoriHole o) {
            return this.seq - o.seq;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int holeCount = Integer.parseInt(bufferedReader.readLine());
        int[] hole = new int[holeCount];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        for (int i = 0; i < holeCount; i++) {
            hole[i] = Integer.parseInt(stringTokenizer.nextToken()) + i;
        }

        int dotoriCount = Integer.parseInt(bufferedReader.readLine());
        List<Dotori> dotoriList = new ArrayList<>();

        stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        for (int i = 1; i <= dotoriCount; i++) {
            dotoriList.add(new Dotori(i, Integer.parseInt(stringTokenizer.nextToken())));
        }
        Collections.sort(dotoriList);

        int dotoriPointer = 0;
        int holePointer = 0;
        int preHoleSize = 0; // 이거 잘쓰면 시간 줄일텐데 이미 통과해서 패스

        List<DotoriHole> dotoriHoleList = new ArrayList<>();

        while (dotoriPointer < dotoriCount) {
            if (hole[holePointer] >= dotoriList.get(dotoriPointer).size) {
                dotoriHoleList.add(new DotoriHole(dotoriList.get(dotoriPointer).seq, holePointer + 1));
                dotoriPointer++;
            } else {
                holePointer++;
            }
        }

        Collections.sort(dotoriHoleList);

        StringBuilder stringBuilder = new StringBuilder();

        for (DotoriHole dotoriHole : dotoriHoleList) {
            stringBuilder.append(dotoriHole.hole).append(" ");
        }

        System.out.println(stringBuilder.toString().trim());


    }
}
