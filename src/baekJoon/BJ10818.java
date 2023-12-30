package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ10818 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        bufferedReader.readLine();

        String[] num = bufferedReader.readLine().split(" ");
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < num.length; i++) {
            if (max < parseInt(num[i])) {
                max = parseInt(num[i]);
                
            }
            if(min > parseInt(num[i])){
                min = parseInt(num[i]);

            }
        }

        System.out.print(min + " " + max);
    }
}
