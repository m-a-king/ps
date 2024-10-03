package programmers;

public class 택배배달과수거하기 {

    public static void main(String[] args) {

        int cap = 4;
        int n = 5;
        int[] deliveries = {1, 0, 3, 1, 2};
        int[] pickups = {0, 3, 0, 4, 0};

        int cap1 = 2;
        int n1 = 7;
        int[] d = {1, 0, 2, 0, 1, 0, 2};
        int[] p = {0, 2, 0, 1, 0, 2, 0};

        System.out.println(solution(cap1, n1, d, p));
        System.out.println(solution(cap, n, deliveries, pickups));
//
        System.out.println(solution(1, 5, new int[]{0, 0, 1, 0, 0}, new int[]{0, 0, 0, 0, 0}));
        System.out.println(solution(2, 2, new int[]{0, 0}, new int[]{0, 4}));
//        System.out.println(solution(4, 4, new int[]{25, 24, 51, 0}, new int[]{51, 0, 0, 49}));

        // 4, 4, [25, 24, 51, 0], [51, 0, 0, 49]
    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int target = n - 1;

        int deliveryIndex = n - 1;
        int pickupIndex = n - 1;

        while (deliveryIndex >= 0 || pickupIndex >= 0) {
            // 가장 먼 배달지와 수거지 찾기
            while (deliveryIndex >= 0 && deliveries[deliveryIndex] == 0) {
                deliveryIndex--;
            }
            while (pickupIndex >= 0 && pickups[pickupIndex] == 0) {
                pickupIndex--;
            }
            // 더 먼 곳까지의 거리 계산
            int distance = Math.max(deliveryIndex, pickupIndex) + 1;
            answer += distance * 2L;

            int leftDelivery = cap;
            int leftPickup = cap;

            // 배달 처리
            for (int i = deliveryIndex; i >= 0; i--) {
                if (deliveries[i] == 0) continue;

                if (leftDelivery >= deliveries[i]) {
                    leftDelivery -= deliveries[i];
                    deliveries[i] = 0;
                } else {
                    deliveries[i] -= leftDelivery;
                    deliveryIndex = i;
                    break;
                }
                deliveryIndex = i - 1; // 다음 위치로 이동
            }

            // 수거 처리
            for (int i = pickupIndex; i >= 0; i--) {
                if (pickups[i] == 0) continue;

                if (leftPickup >= pickups[i]) {
                    leftPickup -= pickups[i];
                    pickups[i] = 0;
                } else {
                    pickups[i] -= leftPickup;
                    pickupIndex = i;
                    break;
                }
                pickupIndex = i - 1; // 다음 위치로 이동
            }
        }

        return answer;
    }
}
