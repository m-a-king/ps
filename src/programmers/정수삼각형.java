package programmers;

public class 정수삼각형 {

    public int solution(int[][] t) {
        int answer = 0;

        for(int i=1;i<t.length;i++){
            for(int j=0;j<t[i].length;j++){
                int me = t[i][j];

                if(j>=1) t[i][j] = t[i][j] + t[i-1][j-1];
                if(j==i) continue;
                t[i][j] = Math.max(t[i][j], me + t[i-1][j]);
            }
        }

        for(int last: t[t.length-1]){
            answer = Math.max(answer, last);
        }

        return answer;
    }
}
