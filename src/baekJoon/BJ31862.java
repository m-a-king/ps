package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class BJ31862 {

    static int n, m, k;
    static List<int[]> leftMatch = new ArrayList<>();
    static int[] points;
    static int resultCount = 0;
    static int maxPoints = 0;
    static int maxCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());

        points = new int[n + 1];

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int team1 = Integer.parseInt(stringTokenizer.nextToken());
            int team2 = Integer.parseInt(stringTokenizer.nextToken());
            int result = Integer.parseInt(stringTokenizer.nextToken());

            if (result == 1) {
                points[team1]++;
            } else if (result == 2) {
                points[team2]++;
            } else if (result == 0) {
                leftMatch.add(new int[]{team1, team2});
            }
        }

        // 초기 최고 점수와 그 점수를 가진 팀의 수를 계산
        for (int i = 1; i <= n; i++) {
            if (points[i] > maxPoints) {
                maxPoints = points[i];
                maxCount = 1;
            } else if (points[i] == maxPoints) {
                maxCount++;
            }
        }

        dfs(0);
        System.out.println(resultCount);
    }

    static void dfs(int matchIndex) {
        if (matchIndex == leftMatch.size()) {
            if (isBest()) {
                resultCount++;
            }
            return;
        }

        int[] match = leftMatch.get(matchIndex);
        int team1 = match[0];
        int team2 = match[1];

        // team1 승리
        points[team1]++;
        int oldMaxPoints = maxPoints;
        int oldMaxCount = maxCount;
        if (points[team1] > maxPoints) {
            maxPoints = points[team1];
            maxCount = 1;
        } else if (points[team1] == maxPoints) {
            maxCount++;
        }
        dfs(matchIndex + 1);

        // 백트래킹
        points[team1]--;
        maxPoints = oldMaxPoints;
        maxCount = oldMaxCount;

        // team2 승리
        points[team2]++;
        if (points[team2] > maxPoints) {
            maxPoints = points[team2];
            maxCount = 1;
        } else if (points[team2] == maxPoints) {
            maxCount++;
        }
        dfs(matchIndex + 1);
        points[team2]--;
    }

    static boolean isBest() {
        return points[k] == maxPoints && maxCount == 1;
    }
}