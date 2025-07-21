package programmers;

import java.util.PriorityQueue;

public class 호텔대실 {

    public static void main(String[] args) {
        new Solution().solution(
                new String[][]{
                        {"15:00", "17:00"},
                        {"16:40", "18:20"},
                        {"14:20", "15:20"},
                        {"14:10", "19:20"},
                        {"18:20", "21:20"}});


    }

    static class Solution {
        public int solution(String[][] book_time) {
            int answer = 0;

            PriorityQueue<Book> waiting = new PriorityQueue<>();
            PriorityQueue<Integer> using = new PriorityQueue<>();

            for (String[] each : book_time) {
                String start = each[0];
                String end = each[1];

                final String[] startHM = start.split(":");
                final String[] endHM = end.split(":");

                final Book book = new Book(Integer.parseInt(startHM[0]) * 60 + Integer.parseInt(startHM[1]),
                        Integer.parseInt(endHM[0]) * 60 + Integer.parseInt(endHM[1]));

                waiting.add(book);
            }

            assert waiting.peek() != null;
            int time = 0;
            int acc = 0;

            while(!waiting.isEmpty()) {
                Book curr = waiting.poll();
                time = curr.start;

                while (!using.isEmpty()) {
                    final int cleanFinishedTime = using.peek();
                    if (cleanFinishedTime > time) {
                        break;
                    }

                    if (cleanFinishedTime <= time) {
                        acc--;
                        using.poll();
                    }
                }

                using.add(curr.end + 10);
                acc++;

                answer = Math.max(answer, acc);
            }

            System.out.println(answer);
            return answer;
        }

        static class Book implements Comparable<Book> {
            int start;
            int end;

            public Book(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public int compareTo(Book o) {
                return start - o.start;
            }
        }
    }
}
