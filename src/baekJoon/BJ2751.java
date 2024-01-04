package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ2751 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(bufferedReader.readLine());
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = parseInt(bufferedReader.readLine());
        }

        quickSort(numbers, 0, n-1);

        for (int number : numbers) {
            System.out.println(number);
        }
    }

    public static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int targetIndex = partition(arr, low, high);

            quickSort(arr, low, targetIndex - 1);
            quickSort(arr, targetIndex + 1, high);
        }
    }

    public static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int x = low - 1;
        for (int i = low; i < high; i++) {
            if (arr[i] <= pivot) {
                x++;

                int temp = arr[x];
                arr[x] = arr[i];
                arr[i] = temp;
            }
        }

        int temp = arr[x + 1];
        arr[x + 1] = pivot; // arr[high]
        arr[high] = temp;
        return x + 1;
    }

}