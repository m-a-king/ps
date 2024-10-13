package programmers;

public class P순위 {

    public int solution(int n, int[][] results) {
        int answer = n;

        int[][] adjMat = new int[n+1][n+1];
        for(int[] r:results){
            int winner = r[0];
            int loser = r[1];

            adjMat[winner][loser] = 1;
            adjMat[loser][winner] = -1;
        }

        for(int mid=1;mid<=n;mid++){
            for(int start=1;start<=n;start++){
                for(int end=1;end<=n;end++){
                    if(adjMat[start][end] != 0) continue;

                    if(adjMat[start][mid] == 1 && adjMat[mid][end] == 1){
                        adjMat[start][end] = 1;
                        adjMat[end][start] = -1;
                    }
                }
            }
        }

        // for(int i=1;i<=n;i++){
        //     for(int j=1;j<=n;j++){
        //         System.out.print(adjMat[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(i==j) continue;
                if(adjMat[i][j] == 0) {
                    answer--;
                    break;
                }
            }
        }

        return answer;
    }
}
