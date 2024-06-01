package test;

public class GuessSort {
    public static void main(String[] args) {
        String[] arr = {"navy", "plum", "coal", "jade", "blue", "pink", "rose", "gray", "teal", "ruby", "mint", "lime", "silk", "corn", "bark", "wine", "dusk", "leaf", "herb", "sage", "cafe", "mist", "pine", "palm"};

        System.out.println("Original array:");
        printArray(arr);

        heapSort(arr);

        System.out.println("Sorted array:");
        printArray(arr);
    }

    public static void heapSort(String[] arr) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
            System.out.println("Array after heapifying index " + i + ":");
            printArray(arr);
        }

        System.out.println("Array after building max heap:");
        printArray(arr);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            String temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call max heapify on the reduced heap
            heapify(arr, i, 0);

            System.out.println("Array after extracting element " + (n - i) + ":");
            printArray(arr);
        }
    }

    public static void heapify(String[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (left < n && arr[left].compareTo(arr[largest]) > 0) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && arr[right].compareTo(arr[largest]) > 0) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            String swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    public static void printArray(String[] arr) {
        for (String s : arr) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
