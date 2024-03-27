package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2744 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        String answer = "";

        for(int i = 0; i< input.length();i++){
            char c = input.charAt(i);
            if(Character.isUpperCase(c)){
                answer += Character.toLowerCase(c);
            } else{
                answer += Character.toUpperCase(c);
            }
        }

        System.out.println(answer);
    }
}
