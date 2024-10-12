package programmers;

public class P등굣길 {

    private static class Pos{
        int row,col;

        Pos(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    public int solution(int m, int n, int[][] ps) {
        int answer = 0;

        int[][] map = new int[n][m];
        for(int[] p:ps){
            int row = p[1]-1;
            int col = p[0]-1;

            map[row][col] = -1;
        }

        for(int i=0;i<n;i++){
            if(map[i][0] == -1) break;
            map[i][0] = 1;
        }

        for(int i=0;i<m;i++){
            if(map[0][i] == -1) break;
            map[0][i] = 1;
        }

        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(map[i][j] == -1) continue;
                int above = map[i-1][j] == -1 ? 0 : map[i-1][j];
                int left = map[i][j-1] == -1 ? 0 : map[i][j-1];
                map[i][j] += (above + left) % 1_000_000_007;
            }
        }

        answer = map[n-1][m-1] % 1_000_000_007;

//         for(int i=0;i<n;i++){
//             for(int j=0;j<m;j++){
//                 System.out.print(map[i][j] + "  ");
//             }
//             System.out.println();
//         }
        return answer;
    }
}
