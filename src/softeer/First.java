package softeer;

import java.io.*;
import java.util.Arrays;

class First {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String input = bufferedReader.readLine();
        String[] inputNums = input.split(" ");

        Arrays.sort(inputNums, (a, b) -> (b+a).compareTo(a+b));
        String maxV = "";
        String minV = "";

        for (int i = 0; i < inputNums.length; i++) {
//            System.out.println(inputNums[i]);
            maxV += inputNums[i];
            minV += inputNums[inputNums.length - i - 1];
        }

//        System.out.println(maxV);
//        System.out.println(minV);

        int res = Integer.valueOf(maxV) + Integer.valueOf(minV);

        System.out.println(res);
    }
}
