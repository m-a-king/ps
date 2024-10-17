package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 우찬2 {

    public static void main(String[] args) {

        int[] case1 = {4, 8, 10, 11, 2, 5};
//        int[] case1 = {1, 2, 3, 4, 4, 5, 5, 6};

        Arrays.sort(case1); // 2 4 5 8 10 11

        int target = case1[0] + case1[case1.length - 1];
        int p1 = 1;
        int p2 = case1.length - 2;

        List<Integer> answerList = new ArrayList<>();

        while (p1 <= p2) {
            if (case1[p1] + case1[p2] == target) {
                p1++;
                p2--;
                continue;
            }

            if (case1[p1] + case1[p2] > target) {
                answerList.add(target-case1[p2--]);
                continue;
            }

            if (case1[p1] + case1[p2] < target) {
                answerList.add(target - case1[p1++]);
            }
        }

        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);

        System.out.println("case = " + Arrays.toString(case1));
        System.out.println("answerList = " + answerList);
    }
}
