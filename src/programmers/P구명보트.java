package programmers;

import java.util.Arrays;

public class P구명보트 {

    public int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);

        int p1 = people.length - 1;
        int p2 = 0;

        while(p1 > p2){


            int currWeight = 0;

            currWeight += people[p1--];

            if(currWeight + people[p2] <= limit){
                currWeight += people[p2++];
            }

            answer++;
        }

        if(p1==p2){
            answer++;
        }

        return answer;
    }
}
