package test;


public class DStest {

    public static void main(String[] args) {

        int[] tc = {1, 10, 3, 8, 5, 6, 7, 4, 9, 2, -100, -101};
        System.out.println("입력");
        for (int num : tc) {
            System.out.print(num + " ");
        }
        sortTest(tc);
        System.out.println();
        System.out.println("출력");
        for (int num : tc) {
            System.out.print(num + " ");
        }
    }

    private static void sortTest(int[] a) {
        int N = a.length;

        for (int i = 0; i < 1; i++) {
            for (int j = N - 1; j > i; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                }
            }
        }
    }

    private static boolean less(int v, int w) {
        return v < w;
    }

    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}