package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ20529 {

    static String[] MBTIs;

    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(bufferedReader.readLine());

        for (int t = 0; t < tc; t++) {

            int n = Integer.parseInt(bufferedReader.readLine());

            MBTIs = new String[n];
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

            if (n > 32) {
                System.out.println(0);
                continue;
            }

            for (int i = 0; i < n; i++) {
                MBTIs[i] = stringTokenizer.nextToken();
            }

            for (int i = 0; i < n - 2; i++) {
                if (res == 0) {
                    break;
                }
                for (int j = i + 1; j < n - 1; j++) {
                    if (res == 0) {
                        break;
                    }
                    for (int k = j + 1; k < n; k++) {
                        res = Math.min(res, calcMBTIdist(i, j, k));
                        if (res == 0) {
                            break;
                        }
                    }
                }
            }

            System.out.println(res);
            res = Integer.MAX_VALUE;

        }
    }

    private static int calcMBTIdist(int i, int j, int k) {
        int cnt = 0;

        for (int m = 0; m < 4; m++) {
            cnt += MBTIs[i].charAt(m) != MBTIs[j].charAt(m) ? 1 : 0;
            cnt += MBTIs[j].charAt(m) != MBTIs[k].charAt(m) ? 1 : 0;
            cnt += MBTIs[k].charAt(m) != MBTIs[i].charAt(m) ? 1 : 0;
        }

        return cnt;
    }
}
