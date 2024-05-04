package programmers;

import java.util.Arrays;

public class 가장큰수 {

    class Solution {
        public String solution(int[] numbers) {

            String[] arr = new String[numbers.length];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = String.valueOf(numbers[i]);
            }
            Arrays.sort(arr, (a, b) -> (b+a).compareTo(a+b));

            if (arr[0].equals("0")) {
                return "0";
            }

            StringBuilder stringBuilder = new StringBuilder();

            for (String s : arr) {
                stringBuilder.append(s);
            }

            return stringBuilder.toString();
        }
    }
}
