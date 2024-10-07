package programmers;
import java.util.*;

public class P더맵게 {

    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int s:scoville){
            pq.offer(s);
        }

        int count = 0;
        while(pq.size()>1){
            int first = pq.poll();
            if(first >= K) return count;
            count++;
            int second = pq.poll();

            int res = first + second * 2;
            pq.offer(res);
        }

        if(pq.poll() < K) return -1;
        return count;
    }
}
