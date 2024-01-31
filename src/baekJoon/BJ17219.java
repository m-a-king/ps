package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedWriter;
import java.util.HashMap;
import java.util.Map;

public class BJ17219 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        Map<String, String> idpw = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String[] IDPW = bufferedReader.readLine().split(" ");
            String ID = IDPW[0];
            String PW = IDPW[1];

            idpw.put(ID, PW);
        }

        for (int i = 0; i < M; i++) {
            System.out.println(idpw.get(bufferedReader.readLine()));
        }

    }
}
