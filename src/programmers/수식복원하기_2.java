package programmers;

import java.util.Arrays;

public class 수식복원하기_2 {

    public static void main(String[] args) {

        String[] expressions = {
                "1 + 5 = 10",
                "23 + 12 = X",
                "55 + 31 = X",  // 올림 발생
                "52 - 15 = X",
                "53 - 24 = X"  // 빌림 발생
        };
        String[] solution = solution(expressions);

        System.out.println(definiteDigit);

        for (String expr : solution) {
            System.out.println(expr);
        }

    }

    static int guessLowerDigit = 2;
    static int definiteDigit = -1;
    static boolean[] visited;
    static String[] answer;
    static int answerIdx = 0;

    public static String[] solution(String[] expressions) {
        answer = new String[expressions.length];
        visited = new boolean[expressions.length];

        for (int i = 0; i < expressions.length; i++) {
            String exp = expressions[i];
            String[] tokens = exp.split(" ");

            int a = Integer.parseInt(tokens[0]);
            String operator = tokens[1];
            int b = Integer.parseInt(tokens[2]);
            String res = tokens[4];

            int a_1 = a % 10;
            int a_2 = a / 10;

            int b_1 = b % 10;
            int b_2 = b / 10;

            int currMaxDigit = Math.max(Math.max(a_1, a_2), Math.max(b_1, b_2));

            guessLowerDigit = Math.max(currMaxDigit + 1, guessLowerDigit);

            if (res.equals("X")) continue;
            visited[i] = true;
            int intRes = Integer.parseInt(res);
            int res_1 = intRes % 10;
            int res_2 = intRes % 100 / 10;
            int res_3 = intRes / 100;

            guessLowerDigit = Math.max(guessLowerDigit, Math.max(res_1, res_2) + 1);

            if (operator.equals("+")) {
                // 13 + 14 -> real 32 expect 27 real 30 expect 27
                int expect = a_1 + b_1;
                int diff = expect - res_1;
                int temp = diff == 0 ? 0 : 1;

                if (temp == 1) {
                    definiteDigit = diff;
                    break;
                }

                // 31 31 -> real 101 expect 61
                expect = a_2 + b_2;
                diff = expect - res_2;
                if (diff == 0) continue;
                definiteDigit = diff;
            } else {
                // 35 - 23
                // 33 - 23
                if (a_1 >= b_1) {
                    continue;
                }

                // 32 - 25 expect 7 real 3
                // 21 - 12 expect 9 real 2
                int expect = 10 + a_1 - b_1;

                int diff = expect - res_1;
                definiteDigit = 10 - diff;
            }
        }

        if (guessLowerDigit == 9) {
            definiteDigit = 9;
        }

        for (int i = 0; i < expressions.length; i++) {
            if (visited[i]) continue;

            String exp = expressions[i];
            String[] tokens = exp.split(" ");

            int a = Integer.parseInt(tokens[0]);
            String operator = tokens[1];
            int b = Integer.parseInt(tokens[2]);
            String res = tokens[4];

            if(!res.equals("X")) continue;

            int a_1 = a % 10;
            int a_2 = a / 10;

            int b_1 = b % 10;
            int b_2 = b / 10;

            // 추측된 진수로 진행
            if (definiteDigit == -1) {
                if (operator.equals("+")) {
                    // 13 + 14 -> real 32 expect 27 real 30 expect 27

                    if (a_1 + b_1 >= guessLowerDigit || a_2 + b_2 >= guessLowerDigit) {
                        answer[answerIdx++] = a + " " + operator + " " + b + " = ?";
                        continue;
                    }

                    answer[answerIdx++] = a + " " + operator + " " + b + " = " + (a + b);
                } else {
                    if (a_1 < b_1) {
                        answer[answerIdx++] = a + " " + operator + " " + b + " = ?";
                        continue;
                    }

                    answer[answerIdx++] = a + " " + operator + " " + b + " = " + (a - b);
                }
            }
            // 확정된 진수로 진행
            else {
                if (operator.equals("+")) {
                    calc(a, b, operator);
                } else {
                    calc(a, b, operator);
                }
            }
        }


        return Arrays.copyOf(answer, answerIdx);
    }

    private static void calc(int a, int b, String operator) {
        int a_1 = a % 10;
        int a_2 = a / 10;

        int b_1 = b % 10;
        int b_2 = b / 10;

        int temp = 0;
        int res_1 = 0;
        int res_2 = 0;
        int res_3 = 0;

        if (operator.equals("+")) {

            if (a_1 + b_1 >= definiteDigit) {
                temp = 1;
                res_1 = a_1 + b_1 - definiteDigit;
            } else {
                res_1 = a_1 + b_1;
            }

            if (a_2 + b_2 + temp >= definiteDigit) {
                res_3 = 1;
                res_2 = a_2 + b_2 + temp - definiteDigit;
            } else {
                res_2 = a_2 + b_2 + temp;
            }
            answer[answerIdx++] = a + " " + operator + " " + b + " = " + (res_1 + res_2 * 10 + res_3 * 100);
        } else {
            if (a_1 < b_1) {
                a_2--;
                a_1 += definiteDigit; //////////
                res_1 = a_1 - b_1;
            } else {
                res_1 = a_1 - b_1;
            }

            res_2 = a_2 - b_2;

            answer[answerIdx++] = a + " " + operator + " " + b + " = " + (res_1 + res_2 * 10);
        }
    }
}
