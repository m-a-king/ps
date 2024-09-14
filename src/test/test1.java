package test;

import java.util.Arrays;

public class test1 {

    static int startRow, startCol, endRow, endCol;

    public static int[] solution(String[] wallpaper) {
        int[] answer;

         startRow = 0;
         startCol = 0;

        init(wallpaper);

        endRow = 0;
        endCol = 0;

        for (int i = startRow; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[0].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    endRow = i;

                    if (startCol > j) {
                        startCol = j;
                    } else {

                        endCol = Math.max(endCol,j);
                    }
                }
            }
        }

        answer = new int[]{startRow, startCol, endRow + 1, endCol + 1};
        return answer;
    }

    public static void init(String[] wallpaper) {
        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[0].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    startRow = i;
                    startCol = j;
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {

        String[] input = {"..........", ".....#....", "......##..", "...##.....", "....#....."};
        int[] answer = solution(input);

        System.out.println(Arrays.toString(answer));
    }
}
