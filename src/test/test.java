//package test;
//
//public class test {
//
//    public static void main(String[] args) {
//
//        int num = 4;
//
//        while (num > 0) {
//            try {
//                System.out.println("계산결과 :"+6/(--num))";
//            } catch (ArithmeticException e) {
//                System.out.println("오류");
//            }
//        }
//    }
//}


package test;

public class test {

    public static void main(String[] args) {

        int[][] A = {{1, 2, 3}, {4, 5, 7}, {6, 2, 3}};
        int[][] B = {{5, 1, 7}, {2, 8, 7}, {5, 2, 9}};
        int[][] C;

        C = sumArr(A, B);

        System.out.println("C matrix is:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] sumArr(int[][] a, int[][] b) {
        int rowSize = Math.min(a.length, b.length);
        int[][] c = new int[rowSize][];

        for (int i = 0; i < a.length; i++) {
            int colSize = Math.min(a[i].length, b[i].length);
            c[i] = new int[colSize];

            for (int j = 0; j < a[0].length; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }

        return c;
    }
}