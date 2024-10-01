package programmers;

public class 산모양타일링 {

    public static void main(String[] args) {

        int[] tops = {1, 1, 0, 1};
        int n = 4;


        System.out.println(solution(n, tops));

    }

    public static int solution(int n, int[] tops) {
        int answer = 0;

        int[] dp1 = new int[n]; // 1,2,3번 타일만 사용
        int[] dp2 = new int[n]; // 4번 타일 사용

        dp1[0] = tops[0] == 1 ? 3 : 2;
        dp2[0] = 1;

        for (int i = 1; i < n; i++) {
            dp1[i] = tops[i] == 1 ? dp1[i - 1] * 3 + dp2[i - 1] * 2 : dp1[i - 1] * 2 + dp2[i - 1];
            dp2[i] = dp1[i - 1] + dp2[i - 1];

            dp1[i] %= 10007;
            dp2[i] %= 10007;
        }

        answer = (dp1[n - 1] + dp2[n - 1]) % 10007;


        return answer ;
    }
}
