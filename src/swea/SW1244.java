package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SW1244 {
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String[] inputs = br.readLine().split(" ");
            char[] numbers = inputs[0].toCharArray();
            int change = Integer.parseInt(inputs[1]);

            max = 0; // 최대 상금 초기화
            findMaxValue(numbers, change, 0);
            System.out.println("#" + tc + " " + max);
        }
    }

    // 숫자판 교환 및 최대값 탐색
    private static void findMaxValue(char[] numbers, int change, int index) {
        if (change == 0 || index == numbers.length) {
            int value = calculateValue(numbers);
            max = Math.max(max, value);
            return;
        }

        for (int i = index; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                // 숫자판 교환
                swap(numbers, i, j);
                findMaxValue(numbers, change - 1, i);
                // 원래 상태로 복구
                swap(numbers, i, j);
            }
        }
    }

    // 두 숫자의 위치를 교환
    private static void swap(char[] numbers, int i, int j) {
        char temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    // 숫자판 배열로부터 가치 계산
    private static int calculateValue(char[] numbers) {
        return Integer.parseInt(new String(numbers));
    }
}

// TotalCases = Sum of (n-i-1) * Cases for (change-1) for i from 0 to n-1
// brute force
