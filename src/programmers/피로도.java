package programmers;

public class 피로도 {

    class Solution {
        static boolean[] visited;
        static int dungeonCount;
        static int[][] dungeons;
        static int max;

        // 사용자 정의 예외 클래스
        static class AllDungeonsVisitedException extends Exception {}

        public static int solution(int k, int[][] dungeons_input) {
            dungeonCount = dungeons_input.length;
            visited = new boolean[dungeonCount];
            dungeons = dungeons_input;
            max = 0;

            try {
                selectDungeons(k, 0);
            } catch (AllDungeonsVisitedException e) {
                return dungeonCount;
            }
            return max;
        }

        private static void selectDungeons(int leftFatigue, int count) throws AllDungeonsVisitedException {
            if (count == dungeonCount) {
                throw new AllDungeonsVisitedException(); // 최대 방문 가능한 던전 수에 도달
            }

            for (int i = 0; i < dungeonCount; i++) {
                if (!visited[i] && leftFatigue >= dungeons[i][0]) {
                    visited[i] = true;
                    selectDungeons(leftFatigue - dungeons[i][1], count + 1);
                    visited[i] = false;
                }
            }
            max = Math.max(max, count);
        }
    }
}
