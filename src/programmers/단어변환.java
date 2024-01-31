package programmers;

import java.util.*;

public class 단어변환 {

    private static class Node {
        String word;
        int depth;

        public Node(String word, int depth) {
            this.word = word;
            this.depth = depth;
        }
    }

    private boolean isOneCharDifferent(String word1, String word2) {
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
                if (diffCount > 1) {
                    return false;
                }
            }
        }
        return diffCount == 1;
    }

    public int solution(String begin, String target, String[] words) {
        int answer = 0;

        boolean isPresent = Arrays.asList(words).contains(target);

        if (isPresent) {
            int length = words.length;
            Queue<Node> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[length];
            queue.offer(new Node(begin, 0));

            while (!queue.isEmpty()) {
                Node cNode = queue.poll();
                String current = cNode.word;
                int cDepth = cNode.depth;

                if (current.equals(target)) {
                    answer = cDepth;
                    return answer;
                }

                for (int i = 0; i < length; i++) {
                    if (!visited[i]) {
                        if (isOneCharDifferent(current, words[i])) {
                            visited[i] = true;
                            queue.offer(new Node(words[i], cDepth+1));
                        }
                    }
                }
            }
        }

        return answer;
    }
}
