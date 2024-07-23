package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW1289 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(bufferedReader.readLine());

        for (int t = 0; t < tc; t++) {
            String s = bufferedReader.readLine();
            int count = 1;
            boolean flag = false;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c == '0') {
                    continue;
                }
                if (c == '1') {
                    char last = '1';

                    for (; i < s.length(); i++) {
                        c = s.charAt(i);

                        if (c != last) {

                            count++;
                            last = c;
                        }
                    }

                    flag = true;
                }

                if (flag) {
                    break;
                }
            }

            System.out.println(count);


        }
    }
}
