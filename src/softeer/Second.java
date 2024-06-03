package softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Second {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

        String command = stringTokenizer.nextToken();
        String secretKey = stringTokenizer.nextToken();
        int rotateCnt = Integer.parseInt(stringTokenizer.nextToken());
        String message = stringTokenizer.nextToken();


        if (command.equals("encrypt")) {
            char[] res = encrypt(secretKey, message);

            rotateL(res, rotateCnt);


        } else {
            char[] chars = rotateR(message, rotateCnt);

            decrypt(secretKey, chars);

        }

    }

    private static char[] rotateR(String message, int rotateCnt) {
        char[] res = new char[message.length()];
        int idx = 0;
        rotateCnt %= message.length();

        for (int i = message.length() - rotateCnt; i < message.length(); i++) {
            res[idx++] = message.charAt(i);
        }
        for (int i = 0; i < message.length() - rotateCnt; i++) {
            res[idx++] = message.charAt(i);
        }
        return res;
    }


    private static void rotateL(char[] res, int rotateCnt) {
        rotateCnt %= res.length;

        for (int i = rotateCnt; i < res.length; i++) {
            System.out.print(res[i]);
        }
        for (int i = 0; i < rotateCnt; i++) {
            System.out.print(res[i]);
        }
    }

    private static char[] encrypt(String secretKey, String message) {
        char[] res = new char[message.length()];

        for (int i = 0; i < message.length(); i++) {
            res[i] = (char) (((message.charAt(i) + secretKey.charAt(i) - 194) % 26) + 97); // 97 * 2
        }
        return res;
    }

    private static int[] decrypt(String secretKey, char[] message) {
        int[] res = new int[message.length];

        for (int i = 0; i < message.length; i++) {
            int diff = message[i] - secretKey.charAt(i);
            res[i] = diff >= 0 ? diff : 26 + diff;
            System.out.print((char) (res[i]+97));

        }
        return res;

    }

}


//    cspkfcgzin 3
//    spkfcgzin 4
//    pkfcgzin 5
//    kfcgzin 6
//    fcgzin 7
//    cgzin 8
//    gzin 9
//    zin 10
//    in 11
//    n 12
//     13