import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] weights = new int[3];

        for (int i = 0; i < 3; i++) {
            weights[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(weights);
        System.out.println(weights[1]);
    }
}