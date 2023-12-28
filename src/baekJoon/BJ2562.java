package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ2562 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int max = 0;
        int maxIndex = -1;
        for(int i = 0; i<9; i++){

            int number = parseInt(bufferedReader.readLine());
            if(number>max){
                max = number;
                maxIndex = i;
            }
        }

        System.out.println(max);
        System.out.println(maxIndex+1);
    }

}
