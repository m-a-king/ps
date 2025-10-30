package programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class 네트워크 {
    public int solution(int n, int[][] computers) {
        int answer = 0;

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque();

        for(int i=0;i<n;i++) {
            if(visited[i]) continue;
            visited[i] = true;

            queue.offer(i);
            answer++;

            while(!queue.isEmpty()) {
                int curr = queue.poll();

                for(int j=0;j<n;j++){
                    if(j==curr) continue;
                    if(visited[j]) continue;
                    if(computers[curr][j] == 0) continue;
                    visited[j] = true;
                    queue.offer(j);
                }
            }
        }

        return answer;
    }
}
