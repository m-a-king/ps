package baekJoon;

import java.io.*;

import static java.lang.Integer.parseInt;

public class BJ2751 {

    static int[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = parseInt(bufferedReader.readLine());
        int[] numbers = new int[n];
        temp = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = parseInt(bufferedReader.readLine());
        }

        mergeSort(numbers, 0, n-1);

        for (int number : numbers) {
            bufferedWriter.write(number + "\n");
        }
        bufferedWriter.flush();  // 버퍼에 남아 있는 데이터를 모두 출력
        bufferedWriter.close();  // 리소스 해제
    }

    private static void mergeSort(int[] numbers, int left, int right) {

        if (left < right) {
            int mid = (left+right)/2;

            mergeSort(numbers, left, mid);
            mergeSort(numbers, mid+1, right);

            merge(numbers, left, mid, right);
        }

    }

    private static void merge(int[] numbers, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (numbers[i] <= numbers[j]) {
                temp[k++] = numbers[i++];
            }else {
                temp[k++] = numbers[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = numbers[i++];
        }

        while (j <= right) {
            temp[k++] = numbers[j++];
        }

        for (int l = left; l <= right; l++) {
            numbers[l] = temp[l];
        }
    }


}