package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ1759 {

    static int L;
    static int C;
    static char[] alp;
    static Set<Character> moSet = new HashSet<>(Set.of('a', 'e', 'i', 'o', 'u'));
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        L = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        alp = new char[C];

        for (int i = 0; i < C; i++) {
            alp[i] = stringTokenizer.nextToken().charAt(0);
        }

        Arrays.sort(alp);

        for (int i = 0; i < C; i++) {
            bfs(i);
        }

        System.out.println(answer);
    }

    private static void bfs(int start) {
        Queue<Word> q = new ArrayDeque<>();

        final char startAlp = alp[start];
        final boolean isMoStart = moSet.contains(startAlp);

        q.offer(new Word(startAlp + "", start,
                isMoStart ? 0 : 1,
                isMoStart ? 1 : 0));

        while (!q.isEmpty()) {
            final Word curr = q.poll();

            if (curr.mo + curr.ja == L) {
                if (curr.mo > 0 && curr.ja > 1) {
                    answer.append(curr.value).append("\n");
                }
            }

            for (int i = curr.lastIdx + 1; i < C; i++) {
                final char nextAlp = alp[i];
                final boolean isMo = moSet.contains(nextAlp);

                q.offer(new Word(curr.value + nextAlp, i,
                        isMo ? curr.ja : curr.ja + 1,
                        isMo ? curr.mo + 1 : curr.mo));
            }
        }
    }

    private static class Word {
        String value;
        int lastIdx;
        int ja;
        int mo;

        public Word(String value, int lastIdx, int ja, int mo) {
            this.value = value;
            this.lastIdx = lastIdx;
            this.ja = ja;
            this.mo = mo;
        }
    }
}
