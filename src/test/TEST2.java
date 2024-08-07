package test;

public class TEST2 {
    public static void main(String[] args) {
        int r1 = 1;
        int r2 = 1000000;
        long result = solution(r1, r2);
        System.out.println(result); // 두 원 사이의 정수 좌표의 개수를 출력합니다.
    }

    public static long solution(int r1, int r2) {
        long count = 0;

        long r1r1 = (long) r1 * r1;
        long r2r2 = (long) r2 * r2;

        for (int x = 1; x <= r2; x++) {
            long xx = (long) x *x;

            long y1 = (long) Math.ceil(Math.sqrt(r1r1 - xx)); // 내부 원
            long y2 = (long) Math.floor(Math.sqrt(r2r2 - xx)); // 외부 원

            count += (y2 - y1 + 1); // 해당 x 값에 대한 유효한 y 값 개수
        }

        // 전체 사분면에 대한 개수를 계산
        return count * 4;
    }
}