package test;

public class BoundExample {

    public static void main(String[] args) {
        int[] array = {1, 1, 2, 2, 2, 3, 3, 3, 4};
        int target = 2;

        int lowerBoundIndex = lowerBound(array, target);
        int upperBoundIndex = upperBound(array, target);

        System.out.println("Lower Bound of target " + target + ": index = " + lowerBoundIndex + ", value = " + array[lowerBoundIndex]);
        System.out.println("Upper Bound of target " + target + ": index = " + upperBoundIndex + ", value = " + array[upperBoundIndex]);
    }

    public static int lowerBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            System.out.printf("Lower Bound - lo: %d, hi: %d, mid: %d, arr[mid]: %d\n", lo, hi, mid, arr[mid]);
            if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        System.out.printf("Lower Bound result - lo: %d, hi: %d\n", lo, hi);
        return hi;
    }

    public static int upperBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            System.out.printf("Upper Bound - lo: %d, hi: %d, mid: %d, arr[mid]: %d\n", lo, hi, mid, arr[mid]);
            if (arr[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        System.out.printf("Upper Bound result - lo: %d, hi: %d\n", lo, hi);
        return lo;
    }
}