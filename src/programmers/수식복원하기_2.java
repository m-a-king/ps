package programmers;

import java.util.Arrays;

public class 수식복원하기_2 {

    public static void main(String[] args) {

        String[] expressions = {
                "1 + 5 = 10",
                "23 + 12 = X",
                "55 + 31 = X",  // 올림 발생 (Carry occurs)
                "52 - 11 = X",
                "53 - 24 = X"   // 빌림 발생 (Borrow occurs)
        };
        String[] solution = solution(expressions);

        System.out.println(confirmedBase);  // 확정된 진수 출력

        for (String expr : solution) {
            System.out.println(expr);  // 결과 출력
        }

    }

    static int estimatedMinimumBase = 2;   // 추정한 최소 진수 (Initially guessed minimum base)
    static int confirmedBase = -1;         // 확정된 진수 (-1이면 아직 확정되지 않음)
    static boolean[] isVisited;            // 이미 처리된 표현식 표시 (Indicates if an expression has been processed)
    static String[] resultExpressions;     // 결과를 저장할 배열 (Array to store the results)
    static int resultIndex = 0;            // 결과 배열의 인덱스 (Index for the result array)

    public static String[] solution(String[] expressions) {
        resultExpressions = new String[expressions.length];
        isVisited = new boolean[expressions.length];

        // 각 표현식을 검사하여 진수를 추정하고 확정된 진수를 찾는다.
        for (int i = 0; i < expressions.length; i++) {
            String expression = expressions[i];
            String[] tokens = expression.split(" ");

            int operandA = Integer.parseInt(tokens[0]);    // 첫 번째 피연산자
            String operator = tokens[1];                   // 연산자 (+ 또는 -)
            int operandB = Integer.parseInt(tokens[2]);    // 두 번째 피연산자
            String resultStr = tokens[4];                  // 결과 (또는 'X')

            // 각 숫자의 일의 자리와 십의 자리 추출
            int aUnitsDigit = operandA % 10;   // operandA의 일의 자리
            int aTensDigit = operandA / 10;    // operandA의 십의 자리

            int bUnitsDigit = operandB % 10;   // operandB의 일의 자리
            int bTensDigit = operandB / 10;    // operandB의 십의 자리

            // 현재 표현식에서의 최대 자릿수 구하기
            int currentMaxDigit = Math.max(Math.max(aUnitsDigit, aTensDigit), Math.max(bUnitsDigit, bTensDigit));

            // 추정한 최소 진수를 갱신 (현재 최대 자릿수 + 1과 기존 추정 값 중 큰 값)
            estimatedMinimumBase = Math.max(currentMaxDigit + 1, estimatedMinimumBase);

            if (resultStr.equals("X")) continue;  // 결과가 'X'이면 다음으로
            isVisited[i] = true;  // 결과가 있는 표현식은 방문 표시
            int resultValue = Integer.parseInt(resultStr);     // 결과를 정수로 변환
            int resUnitsDigit = resultValue % 10;              // 결과의 일의 자리
            int resTensDigit = (resultValue % 100) / 10;       // 결과의 십의 자리
            int resHundredsDigit = resultValue / 100;          // 결과의 백의 자리

            // 결과의 최대 자릿수에 따라 추정한 최소 진수를 갱신
            estimatedMinimumBase = Math.max(estimatedMinimumBase, Math.max(resUnitsDigit, resTensDigit) + 1);

            if (operator.equals("+")) {
                // 덧셈에서 올림을 고려하여 확정된 진수를 찾는다.
                int expectedSum = aUnitsDigit + bUnitsDigit;       // 일의 자리 예상 합
                int difference = expectedSum - resUnitsDigit;      // 예상 합과 실제 결과의 차이
                int carry = difference == 0 ? 0 : 1;               // 올림 발생 여부 (0: 없음, 1: 발생)

                if (carry == 1) {
                    confirmedBase = difference;  // 차이가 진수와 관련되므로 확정된 진수 설정
                    break;
                }

                expectedSum = aTensDigit + bTensDigit;
                difference = expectedSum - resTensDigit;
                if (difference == 0) continue;
                confirmedBase = difference;  // 차이가 진수와 관련되므로 확정된 진수 설정
            } else {
                // 뺄셈에서 빌림을 고려하여 확정된 진수를 찾는다.
                if (aUnitsDigit >= bUnitsDigit) {
                    continue;  // 빌림이 없으면 다음으로
                }

                int expectedDifference = 10 + aUnitsDigit - bUnitsDigit;    // 빌림이 발생한 일의 자리 계산
                int difference = expectedDifference - resUnitsDigit;
                confirmedBase = 10 - difference;      // 차이를 통해 진수를 계산하여 확정된 진수 설정
            }
        }

        // 추정한 최소 진수가 9인 경우 확정된 진수를 9로 설정 (진수는 최대 9까지)
        if (estimatedMinimumBase == 9) {
            confirmedBase = 9;
        }

        // 결과가 'X'인 표현식에 대해 결과를 계산한다.
        for (int i = 0; i < expressions.length; i++) {
            if (isVisited[i]) continue;  // 이미 처리된 표현식은 넘어감

            String expression = expressions[i];
            String[] tokens = expression.split(" ");

            int operandA = Integer.parseInt(tokens[0]);
            String operator = tokens[1];
            int operandB = Integer.parseInt(tokens[2]);
            String resultStr = tokens[4];

            if (!resultStr.equals("X")) continue;  // 결과가 있는 표현식은 넘어감

            int aUnitsDigit = operandA % 10;   // operandA의 일의 자리
            int aTensDigit = operandA / 10;    // operandA의 십의 자리

            int bUnitsDigit = operandB % 10;   // operandB의 일의 자리
            int bTensDigit = operandB / 10;    // operandB의 십의 자리

            // 확정된 진수가 없는 경우 추정한 최소 진수로 결과를 계산
            if (confirmedBase == -1) {
                if (operator.equals("+")) {
                    // 덧셈에서 추정한 진수로 결과를 계산하고, 올림이 발생하면 '?'로 표시
                    if (aUnitsDigit + bUnitsDigit >= estimatedMinimumBase || aTensDigit + bTensDigit >= estimatedMinimumBase) {
                        resultExpressions[resultIndex++] = operandA + " " + operator + " " + operandB + " = ?";
                        continue;
                    }

                    // 결과를 계산하여 저장
                    resultExpressions[resultIndex++] = operandA + " " + operator + " " + operandB + " = " + (operandA + operandB);
                } else {
                    // 뺄셈에서 추정한 진수로 결과를 계산하고, 빌림이 발생하면 '?'로 표시
                    if (aUnitsDigit < bUnitsDigit) {
                        resultExpressions[resultIndex++] = operandA + " " + operator + " " + operandB + " = ?";
                        continue;
                    }

                    // 결과를 계산하여 저장
                    resultExpressions[resultIndex++] = operandA + " " + operator + " " + operandB + " = " + (operandA - operandB);
                }
            }
            // 확정된 진수가 있는 경우 그 진수로 결과를 계산
            else {
                calculateResult(operandA, operandB, operator);  // 계산 메서드 호출
            }
        }

        return Arrays.copyOf(resultExpressions, resultIndex);  // 결과 배열 반환
    }

    // 확정된 진수를 사용하여 결과를 계산하는 메서드
    private static void calculateResult(int operandA, int operandB, String operator) {
        int aUnitsDigit = operandA % 10;  // operandA의 일의 자리
        int aTensDigit = operandA / 10;   // operandA의 십의 자리

        int bUnitsDigit = operandB % 10;  // operandB의 일의 자리
        int bTensDigit = operandB / 10;   // operandB의 십의 자리

        int carryOrBorrow = 0;   // 올림 또는 빌림을 저장할 변수
        int resUnitsDigit = 0;   // 결과의 일의 자리
        int resTensDigit = 0;    // 결과의 십의 자리
        int resHundredsDigit = 0; // 결과의 백의 자리 (덧셈에서 발생할 수 있음)

        if (operator.equals("+")) {

            // 덧셈에서 일의 자리 계산
            if (aUnitsDigit + bUnitsDigit >= confirmedBase) {
                carryOrBorrow = 1;  // 올림 발생
                resUnitsDigit = aUnitsDigit + bUnitsDigit - confirmedBase;
            } else {
                resUnitsDigit = aUnitsDigit + bUnitsDigit;
            }

            // 덧셈에서 십의 자리 계산
            if (aTensDigit + bTensDigit + carryOrBorrow >= confirmedBase) {
                resHundredsDigit = 1;  // 백의 자리 발생
                resTensDigit = aTensDigit + bTensDigit + carryOrBorrow - confirmedBase;
            } else {
                resTensDigit = aTensDigit + bTensDigit + carryOrBorrow;
            }

            // 최종 결과를 계산하여 저장
            int finalResult = resUnitsDigit + resTensDigit * 10 + resHundredsDigit * 100;
            resultExpressions[resultIndex++] = operandA + " " + operator + " " + operandB + " = " + finalResult;
        } else {
            // 뺄셈에서 일의 자리 계산
            if (aUnitsDigit < bUnitsDigit) {
                aTensDigit--;                   // 빌림 발생으로 십의 자리에서 1 차감
                aUnitsDigit += confirmedBase;    // 일의 자리에 진수를 더함
                resUnitsDigit = aUnitsDigit - bUnitsDigit;
            } else {
                resUnitsDigit = aUnitsDigit - bUnitsDigit;
            }

            // 뺄셈에서 십의 자리 계산
            resTensDigit = aTensDigit - bTensDigit;

            // 최종 결과를 계산하여 저장
            int finalResult = resUnitsDigit + resTensDigit * 10;
            resultExpressions[resultIndex++] = operandA + " " + operator + " " + operandB + " = " + finalResult;
        }
    }
}