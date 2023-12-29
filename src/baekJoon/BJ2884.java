package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ2884 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] split = (bufferedReader.readLine().split(" "));
        int hour = parseInt(split[0]);
        int minute = parseInt(split[1]);

        int total = hour * 60 + minute;
        int earlyWake = total - 45;

        if(earlyWake<0){
            earlyWake += 24*60;
            System.out.println((earlyWake/60) + " " + (earlyWake%60));
        }else{
            System.out.println((earlyWake/60) + " " + (earlyWake%60));
        }
    }
}
