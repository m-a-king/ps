package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BJ15740 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = bufferedReader.readLine().split(" ");

        System.out.println(new BigInteger(nums[0]).add(new BigInteger(nums[1])));
    }
}
