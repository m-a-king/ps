package test;

public class selectionSort {
    public static void main(String[] args) {
        int[] a = {64, 25, 12, 22, 11};
        System.out.println("Unsorted array:");
        printArray(a);

        sort(a);

        System.out.println("Sorted array:");
        printArray(a);
    }

    private static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void sort(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) { // outer loop
            for (int j = N - 1; j > i; j--) {
                if ((a[j] < a[j - 1])) {
                    int temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;

                }
            }
            System.out.println(">>>>state " + i);
            printArray(a);
        }
    }
}

// ["cyan", "cafe", "aqua", "bole", "flax", "bone", "rose", "sage", "fawn", "leaf", "gold", "jade", "lava", "bark", "gray", "buff", "dust", "cyan", "kobi", "silk", "palm", "sand", "ruby", "mint"]
// ["cafe", "bole", "flax", "bone", "aqua", "bark", "gray", "buff", "cyan", "dust", "cyan", "silk", "palm", "sand", "mint", "fawn", "leaf", "gold", "jade", "lava", "rose", "sage", "ruby", "kobi"]