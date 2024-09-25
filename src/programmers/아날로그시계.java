package programmers;

public class 아날로그시계 {

    public static void main(String[] args) {
        System.out.println(solution(0, 0, 0, 23, 59, 59));
    }

    public static int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = -1;

        int start = h1 * 3600 + m1 * 60 + s1;
        int end = h2 * 3600 + m2 * 60 + s2;

        int totalAlarm = calc(end) - calc(start);
        int startAlarm = (h1 == 0 || h1 == 12) && m1 == 0 && s1 == 0 ? 1 : 0;

        return totalAlarm + startAlarm;
    }

    private static int calc(int sec) {
        int minSec = sec * 59 / 3600; // per 1 hour
        int hourSec = sec * 719 / 43200; // per 12 hour

        int res = minSec + hourSec;
        if (sec >= 43200) res--; // 반드시 -1, 조건에 -2 이므로

        return res;

    }
}
