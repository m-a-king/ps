package programmers;

import java.util.Arrays;

public class P지폐접기 {

    public int solution(int[] wallet, int[] b) {
        int answer = 0;

        Arrays.sort(wallet);

        while (true) {
            Arrays.sort(b);

            if (wallet[1] < b[1] || wallet[0] < b[0]) {
                b[1] /= 2;
                answer++;
                continue;
            }

            break;
        }


        return answer;
    }

}
