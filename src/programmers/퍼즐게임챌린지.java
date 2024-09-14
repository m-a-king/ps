package programmers;

public class 퍼즐게임챌린지 {

    private static int[] d, t;
    private static int length;
    private static long l;

    public static int solution(int[] diffs, int[] times, long limit) {

        d = diffs;
        t = times;
        length = d.length;
        l = limit;

        long left = 1;
        long right = l-1;
        long mid = 0;

        while (left <= right) {
            mid = (left + right) / 2;
            System.out.println();
            System.out.println("mid = " + mid);

            if (!canComplete(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println();
        System.out.println(left);
        System.out.println(right);
        return (int)left;
    }

    private static boolean canComplete(long level) {
        long resTime = (long) t[0] * d[0];

        for (int i = 1; i < length; i++) {
            if (d[i] <= level) {
                resTime += t[i];
            } else {
                resTime += (t[i - 1] + t[i]) * (d[i] - level) + t[i];
            }
        }

        System.out.println("level = " + level);
        System.out.println("resTime = " + resTime);
        System.out.println("l = " + l);

        return resTime <= l;

    }

    public static void main(String[] args) {

        System.out.println("res: " + solution(new int[]{1, 5, 3}, new int[]{2, 4, 7}, 30));
    }
}
