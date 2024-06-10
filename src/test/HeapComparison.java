package test;

import java.util.Arrays;

public class HeapComparison {
    static int heapifyComparisons = 0;
    static int heapifyMoves = 0;
    static int insertComparisons = 0;
    static int insertMoves = 0;

    public static void main(String[] args) {
        // Example arrays
        int[] array1 = {3, 1, 2, 5, 9, 6, 8, 7, 4, 12, 11, 15, 14, 13, 10};
        int[] array2 = {
                10, 23, 35, 47, 59, 68, 72, 83, 91, 15, 27, 38, 49, 60, 74,
                85, 97, 100, 18, 29, 40, 52, 63, 75, 88, 92, 21, 33, 44, 55,
                67, 79, 81, 93, 99, 14, 26, 37, 48, 58, 69, 73, 84, 95, 17,
                28, 39, 50, 61, 77, 89, 90, 22, 34, 45, 56, 66, 78, 80, 94,
                98, 13, 25, 36, 46, 57, 70, 71, 82, 96, 16, 30, 41, 53, 65,
                76, 87, 91, 20, 32, 43, 54, 64, 83, 91, 95, 12, 24, 35, 47,
                59, 68, 72, 83, 91, 15, 27, 38, 49, 60, 74
        };


        System.out.println("Array 1:");
        compareHeapMethods(array1);

        // Reset counts for the next array
        heapifyComparisons = 0;
        heapifyMoves = 0;
        insertComparisons = 0;
        insertMoves = 0;

        System.out.println("\narray 2:");
        compareHeapMethods(array2);
    }

    public static void compareHeapMethods(int[] array) {
        int[] arrayCopy = array.clone();

        // Initial array
        System.out.print("Initial Array: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println();

        // Heapify method
        buildHeap(array);
        System.out.print("Heap after Heapify: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Heapify Method:");
        System.out.println("Comparisons: " + heapifyComparisons);
        System.out.println("Moves: " + heapifyMoves);
        System.out.println();

        // Insertion method
        buildHeapByInsertion(arrayCopy);
        System.out.print("Heap after Insertion: ");
        for (int num : arrayCopy) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Insertion Method:");
        System.out.println("Comparisons: " + insertComparisons);
        System.out.println("Moves: " + insertMoves);
    }

    // Heapify method
    public static void buildHeap(int[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
    }

    public static void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n) {
            heapifyComparisons++;
            if (array[left] > array[largest]) {
                largest = left;
            }
        }

        if (right < n) {
            heapifyComparisons++;
            if (array[right] > array[largest]) {
                largest = right;
            }
        }

        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            heapifyMoves++;

            heapify(array, n, largest);
        }
    }

    // Insertion method
    public static void buildHeapByInsertion(int[] array) {
        int n = array.length;
        int[] heap = new int[n];
        int size = 0;

        for (int value : array) {
            insertHeap(heap, size, value);
            size++;
        }

        // Copy heap to array
        System.arraycopy(heap, 0, array, 0, n);
    }

    public static void insertHeap(int[] heap, int size, int value) {
        heap[size] = value;
        int current = size;

        while (current > 0) {
            int parent = (current - 1) / 2;

            insertComparisons++;
            if (heap[current] > heap[parent]) {
                int swap = heap[current];
                heap[current] = heap[parent];
                heap[parent] = swap;
                insertMoves++;
                current = parent;
            } else {
                break;
            }
        }
    }
}

// 삽입 T(n)= O(1)+O(log2)+O(log3)+O(log4)+…+O(log(n))
// 힙화 T(n)= log(n)*1+(log(n)-1)*2+(log(n)-2)*4+(log(n)-3)*8 ... + (log(n)-m)*2^m
