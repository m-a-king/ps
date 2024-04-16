package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BJ13305 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        BigInteger[] roads = new BigInteger[n - 1];
        BigInteger[] cities = new BigInteger[n];

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        for (int i = 0; i < n - 1; i++) {
            roads[i] = BigInteger.valueOf(Long.parseLong(stringTokenizer.nextToken()));
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        for (int i = 0; i < n; i++) {
            cities[i] = BigInteger.valueOf(Long.parseLong(stringTokenizer.nextToken()));
        }

        int roadPointer = 0;
        int cityPointer = 0;
        BigInteger result = roads[roadPointer].multiply(cities[cityPointer]);

        for (int i = 2; i < n; i++) {
            roadPointer++;

            if (cities[roadPointer].compareTo(cities[cityPointer]) < 0) {
                cityPointer = roadPointer;
            }

            result = result.add(roads[roadPointer].multiply(cities[cityPointer]));
        }

        System.out.println(result);


    }
}
