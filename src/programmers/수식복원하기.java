package programmers;

import java.util.*;

public class 수식복원하기 {

    public static void main(String[] args) {

        String[] expressions = {"10 - 2 = X", "30 + 31 = 101", "3 + 3 = X", "33 + 33 = X"};
        String[] solution = solution(expressions);

        for (String expr : solution) {
            System.out.println(expr);
        }

    }

    public static String[] solution(String[] expressions) {
        List<String> answerList = new ArrayList<>();

        List<Integer> possibleBases = new ArrayList<>();

        // 1. 가능한 진법 찾기
        for (int base = 2; base <= 9; base++) {
            boolean validBase = true;

            for (String expression : expressions) {
                String[] tokens = expression.split(" ");

                String A = tokens[0];
                String operator = tokens[1];
                String B = tokens[2];
                String equalsSign = tokens[3];
                String C = tokens[4];

                // 숫자들이 해당 진법에서 유효한지 확인
                if (!isValidNumber(A, base) || !isValidNumber(B, base)) {
                    validBase = false;
                    break;
                }

                // 결과 값이 'X'가 아닌 경우 계산 검증
                if (!C.equals("X")) {
                    if (!isValidNumber(C, base)) {
                        validBase = false;
                        break;
                    }

                    int operand1 = Integer.parseInt(A, base);
                    int operand2 = Integer.parseInt(B, base);
                    int result = Integer.parseInt(C, base);

                    int computedResult;
                    if (operator.equals("+")) {
                        computedResult = operand1 + operand2;
                    } else if (operator.equals("-")) {
                        computedResult = operand1 - operand2;
                        if (computedResult < 0) {
                            validBase = false;
                            break;
                        }
                    } else {
                        validBase = false;
                        break;
                    }

                    if (computedResult != result) {
                        validBase = false;
                        break;
                    }
                }
            }

            if (validBase) {
                possibleBases.add(base);
            }
        }

        // 2. 결과 값 채우기
        for (int i = 0; i < expressions.length; i++) {
            String expression = expressions[i];
            String[] tokens = expression.split(" ");

            String A = tokens[0];
            String operator = tokens[1];
            String B = tokens[2];
            String equalsSign = tokens[3];
            String C = tokens[4];

            if (C.equals("X")) {
                Set<String> possibleResults = new HashSet<>();

                for (int base : possibleBases) {
                    if (!isValidNumber(A, base) || !isValidNumber(B, base)) {
                        continue;
                    }

                    int operand1 = Integer.parseInt(A, base);
                    int operand2 = Integer.parseInt(B, base);

                    int computedResult;
                    if (operator.equals("+")) {
                        computedResult = operand1 + operand2;
                    } else if (operator.equals("-")) {
                        computedResult = operand1 - operand2;
                        if (computedResult < 0) {
                            continue;
                        }
                    } else {
                        continue;
                    }

                    String resultStr = Integer.toString(computedResult, base).toUpperCase();

                    if (!isValidNumber(resultStr, base)) {
                        continue;
                    }

                    possibleResults.add(resultStr);
                }

                if (possibleResults.size() == 1) {
                    String resultValue = possibleResults.iterator().next();
                    String filledExpression = A + " " + operator + " " + B + " = " + resultValue;
                    answerList.add(filledExpression);
                } else {
                    String filledExpression = A + " " + operator + " " + B + " = ?";
                    answerList.add(filledExpression);
                }
            }
        }

        return answerList.toArray(new String[0]);
    }

    // 숫자가 해당 진법에서 유효한지 확인하는 함수
    static boolean isValidNumber(String numStr, int base) {
        for (int i = 0; i < numStr.length(); i++) {
            char c = numStr.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
            int digit = c - '0';
            if (digit >= base) {
                return false;
            }
        }
        return true;
    }
}