package programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class 다리를지나는트럭 {

    static class Solution {

        private static class Truck {
            int weight, leftTime;

            public Truck(int weight, int leftTime) {
                this.weight = weight;
                this.leftTime = leftTime;
            }
        }

        static int sumOfWeightOnBridge = 0;

        public static int solution(int bridge_length, int weight, int[] truck_weights) {
            int answer = 0;

            Queue<Truck> queue = new ArrayDeque<>();

            int idx = 0;
            int time = 0;
            int success = 0;

            while (success != truck_weights.length) {
                time++;

                if (idx < truck_weights.length) {
                    int currentTruckWeight = truck_weights[idx];

                    if (canClimb(weight, currentTruckWeight)) {
                        idx++;
                        sumOfWeightOnBridge += currentTruckWeight;
                        queue.offer(new Truck(currentTruckWeight, bridge_length));
                    }
                }

                for (Truck truck : queue) {
                    if (--truck.leftTime == 0) {
                        queue.poll();
                        success++;
                        sumOfWeightOnBridge -= truck.weight;

                    }
                }
            }


            answer = time;
            return answer;
        }

        private static boolean canClimb(int weight, int currentTruckWeight) {
            return weight >= currentTruckWeight + sumOfWeightOnBridge;
        }

        public static void main(String[] args) {
            System.out.println("imstart");
            int[] truck_weight_input = new int[]{7, 4, 5, 6};
            System.out.println(solution(2, 10, truck_weight_input));
        }
    }
}
