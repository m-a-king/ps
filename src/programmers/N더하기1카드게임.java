package programmers;

import java.util.ArrayList;
import java.util.List;

public class N더하기1카드게임 {

    public static void main(String[] args) {

        System.out.println(solution(4, new int[]{3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4}));
//        System.out.println(solution(3, new int[]{1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12}));
//        System.out.println(solution(2, new int[]{5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7}));
//        System.out.println(solution(10, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18}));

    }


    static int maxTime = 0;
    static int cardLength;
    static int target;

    static boolean recent = false;

    public static int solution(int coin, int[] cards) {
        int answer = 0;

        cardLength = cards.length;
        target = cardLength + 1;

        int seq = cards.length / 3;

        List<Integer> beforeSelect = new ArrayList<>();
        List<Integer> afterSelect = new ArrayList<>();
        for (int i = 0; i < seq; i++) {
            beforeSelect.add(cards[i]);
        }

        while (seq < cardLength) {

            answer++;

            afterSelect.add(cards[seq++]);
            afterSelect.add(cards[seq++]);

            if (canPlay(beforeSelect, beforeSelect)) {
                continue;
            }

            if (coin > 0 && canPlay(beforeSelect, afterSelect)) {
                coin--;
                continue;
            }

            if (coin > 1 && canPlay(afterSelect, afterSelect)) {
                coin -= 2;
                continue;
            }

            break;

        }

        return recent ? answer + 1 : answer;
    }

    private static boolean canPlay(List<Integer> cards1, List<Integer> cards2) {

        cards1.sort((a, b) -> a - b);
        cards2.sort((a, b) -> a - b);

        int beforeLeft = 0;
        int beforeRight = cards1.size() - 1;

        int afterLeft = 0;
        int afterRight = cards2.size() - 1;

        while (beforeLeft <= beforeRight && afterLeft <= afterRight) {
            int sum = cards1.get(beforeLeft) + cards2.get(afterRight);
            if (sum == target) {
                System.out.println("left = " + cards1.get(beforeLeft));
                System.out.println("right = " + cards2.get(afterRight));
                cards2.remove(afterRight);
                cards1.remove(beforeLeft);

                recent = true;
                return true;
            } else if (sum > target) {
                afterRight--;
            } else {
                beforeLeft++;
            }
        }

        recent = false;
        return false;

    }


}
