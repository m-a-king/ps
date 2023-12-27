package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;
import static java.lang.Math.sqrt;

public class BJ1011 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = parseInt(reader.readLine());
        int[] moveCount = new int[count];

        for (int i = 0; i < count; i++) {
            String[] pointArr = reader.readLine().split(" ");
            int x = parseInt(pointArr[0]);
            int y = parseInt(pointArr[1]);
            int distance = y - x;

            moveCount[i] = calculateMoves(distance);
        }

        for (int moves : moveCount) {
            System.out.println(moves);
        }
    }

    private static int calculateMoves(int distance) {
        if (distance <= 2) {
            return distance; // 1 or 2
        }

        int sqrtDis = (int) sqrt(distance);
        int square = sqrtDis * sqrtDis;
        int diff = distance - square;
        int moveCount = 2 * sqrtDis - 1;

        if (diff > 0) {
            moveCount += (diff > sqrtDis) ? 2 : 1;
        }

        return moveCount;
    }
}
