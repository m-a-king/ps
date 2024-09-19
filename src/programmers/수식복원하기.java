package programmers;

import java.util.*;

public class 수식복원하기 {

    public static void main(String[] args) {

        String[] expressions = {"10 - 2 = X", "30 + 31 = 101", "3 + 3 = X", "33 + 33 = X"};
        String[] solution = solution(expressions);

        for (String expr : solution) {
            System.out.println(expr);  // 결과 출력
        }

    }

    public static String[] solution(String[] expressions) {
        List<String> answerList = new ArrayList<>();     // 결과를 저장할 리스트
        List<Integer> possibleBases = new ArrayList<>(); // 가능한 진법들을 저장할 리스트

        // 1. 가능한 진법 찾기
        for (int base = 2; base <= 9; base++) {
            boolean validBase = true;  // 현재 진법이 유효한지 여부를 나타내는 변수

            for (String expression : expressions) {
                String[] tokens = expression.split(" ");

                String A = tokens[0];           // 첫 번째 피연산자
                String operator = tokens[1];    // 연산자 (+ 또는 -)
                String B = tokens[2];           // 두 번째 피연산자
                String equalsSign = tokens[3];  // 등호 (=)
                String C = tokens[4];           // 결과 값 또는 'X'

                // 숫자들이 해당 진법에서 유효한지 확인
                if (!isValidNumber(A, base) || !isValidNumber(B, base)) {
                    validBase = false;
                    break;  // 유효하지 않으면 더 이상 검사할 필요 없음
                }

                // 결과 값이 'X'가 아닌 경우 계산 검증
                if (!C.equals("X")) {
                    if (!isValidNumber(C, base)) {
                        validBase = false;
                        break;
                    }

                    // 해당 진법에서 숫자를 정수로 변환
                    int operand1 = Integer.parseInt(A, base);
                    int operand2 = Integer.parseInt(B, base);
                    int result = Integer.parseInt(C, base);

                    int computedResult;
                    if (operator.equals("+")) {
                        computedResult = operand1 + operand2;  // 덧셈 계산
                    } else if (operator.equals("-")) {
                        computedResult = operand1 - operand2;  // 뺄셈 계산
                        if (computedResult < 0) {
                            validBase = false;  // 결과가 음수이면 유효하지 않음
                            break;
                        }
                    } else {
                        validBase = false;  // 지원하지 않는 연산자
                        break;
                    }

                    // 계산 결과가 실제 결과와 일치하는지 확인
                    if (computedResult != result) {
                        validBase = false;
                        break;
                    }
                }
            }

            // 유효한 진법이면 리스트에 추가
            if (validBase) {
                possibleBases.add(base);
            }
        }

        // 2. 결과 값 채우기
        for (int i = 0; i < expressions.length; i++) {
            String expression = expressions[i];
            String[] tokens = expression.split(" ");

            String A = tokens[0];           // 첫 번째 피연산자
            String operator = tokens[1];    // 연산자
            String B = tokens[2];           // 두 번째 피연산자
            String equalsSign = tokens[3];  // 등호
            String C = tokens[4];           // 결과 값 또는 'X'

            if (C.equals("X")) {
                Set<String> possibleResults = new HashSet<>();  // 가능한 결과 값들을 저장할 집합

                // 가능한 진법들에 대해 결과를 계산
                for (int base : possibleBases) {
                    // 해당 진법에서 숫자들이 유효한지 확인
                    if (!isValidNumber(A, base) || !isValidNumber(B, base)) {
                        continue;
                    }

                    int operand1 = Integer.parseInt(A, base);  // 첫 번째 피연산자 변환
                    int operand2 = Integer.parseInt(B, base);  // 두 번째 피연산자 변환

                    int computedResult;
                    if (operator.equals("+")) {
                        computedResult = operand1 + operand2;  // 덧셈 계산
                    } else if (operator.equals("-")) {
                        computedResult = operand1 - operand2;  // 뺄셈 계산
                        if (computedResult < 0) {
                            continue;  // 결과가 음수이면 스킵
                        }
                    } else {
                        continue;  // 지원하지 않는 연산자
                    }

                    // 계산된 결과를 해당 진법의 문자열로 변환
                    String resultStr = Integer.toString(computedResult, base).toUpperCase();

                    // 결과가 해당 진법에서 유효한지 확인
                    if (!isValidNumber(resultStr, base)) {
                        continue;
                    }

                    possibleResults.add(resultStr);  // 가능한 결과 값 추가
                }

                if (possibleResults.size() == 1) {
                    // 가능한 결과가 하나이면 결과를 채워 넣음
                    String resultValue = possibleResults.iterator().next();
                    String filledExpression = A + " " + operator + " " + B + " = " + resultValue;
                    answerList.add(filledExpression);
                } else {
                    // 여러 결과가 가능하면 '?'로 표시
                    String filledExpression = A + " " + operator + " " + B + " = ?";
                    answerList.add(filledExpression);
                }
            }
        }

        return answerList.toArray(new String[0]);  // 결과를 배열로 변환하여 반환
    }

    // 숫자가 해당 진법에서 유효한지 확인하는 함수
    static boolean isValidNumber(String numStr, int base) {
        for (int i = 0; i < numStr.length(); i++) {
            char c = numStr.charAt(i);
            if (c < '0' || c > '9') {
                return false;  // 숫자가 아닌 문자가 포함된 경우
            }
            int digit = c - '0';
            if (digit >= base) {
                return false;  // 자릿수가 진법보다 크거나 같은 경우
            }
        }
        return true;  // 모든 자릿수가 해당 진법에서 유효한 경우
    }
}