package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class 과제진행하기 {

    public static void main(String[] args) {
        Solution solution = new Solution();

//        String[][] plans = new String[][]
//                {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};

        String[][] plans = new String[][]
                {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"},
                        {"computer", "12:30", "100"}};

        System.out.println("answer = " + Arrays.toString(solution.solution(plans)));

    }

    static class Solution {

        public String[] solution(String[][] input) {
            List<String> answer = new ArrayList<>();

            PriorityQueue<Plan> plans = new PriorityQueue<>();

            for (int i = 0; i < input.length; i++) {
                final String[] hourAndMinute = input[i][1].split(":");
                int hour = Integer.parseInt(hourAndMinute[0]);
                int minute = Integer.parseInt(hourAndMinute[1]);

                plans.offer(new Plan(input[i][0], hour * 60 + minute, Integer.parseInt(input[i][2])));
            }

            Stack<Plan> unFinished = new Stack<>();
            assert plans.peek() != null;
            int currTime = plans.peek().startTime;

            while (!plans.isEmpty()) {
                final Plan curr = plans.poll();
                final Plan next = plans.peek();
                if (next == null) {
                    answer.add(curr.name);
                    break;
                }

                int availableTime = next.startTime - curr.startTime;

                if (curr.processingTime == availableTime) {
                    answer.add(curr.name);
                    continue;
                }

                if (curr.processingTime > availableTime) {
                    curr.processingTime -= availableTime;
                    unFinished.push(curr);
                    continue;
                }

                if (curr.processingTime < availableTime) {
                    answer.add(curr.name);
                    availableTime -= curr.processingTime;

                    exception(unFinished, availableTime, answer);
                }

            }

            exception(unFinished,Integer.MAX_VALUE, answer);

            System.out.println(unFinished);

            return answer.toArray(new String[0]);
        }

        private void exception(Stack<Plan> left, int availableTime, List<String> answer) {
            while (!left.isEmpty()) {
                final Plan plan = left.pop();
                availableTime -= plan.processingTime;

                if (availableTime == 0) {
                    answer.add(plan.name);
                    return;
                }

                if (availableTime > 0) {
                    answer.add(plan.name);
                    continue;
                }

                if (availableTime < 0) {
                    plan.processingTime = availableTime * -1;
                    left.add(plan);
                    return;
                }
            }
        }

        static class Plan implements Comparable<Plan> {
            String name;
            int startTime;
            int processingTime;

            public Plan(String name, int startTime, int processingTime) {
                this.name = name;
                this.startTime = startTime;
                this.processingTime = processingTime;
            }

            @Override
            public int compareTo(Plan o) {
                return this.startTime - o.startTime;
            }

            @Override
            public String toString() {
                return "Plan{" +
                        "name='" + name + '\'' +
                        ", startTime=" + startTime +
                        ", processingTime=" + processingTime +
                        '}';
            }
        }
    }
}
