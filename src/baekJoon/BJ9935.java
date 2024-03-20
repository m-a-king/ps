package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ9935 {

    static Stack<Character> stack = new Stack<>();
    static char[] bombCharacters;
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        String bomb = bufferedReader.readLine();
        bombCharacters = bomb.toCharArray();

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            stack.push(current);

            if (stack.size() >= bombCharacters.length) {
                if (checkBomb()) {
                    for (int j = 0; j < bombCharacters.length; j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (char c : stack) {
            stringBuilder.append(c);
        }

        System.out.println(stringBuilder.toString().isEmpty() ? "FRULA" : stringBuilder);
    }

    private static boolean checkBomb() {
        for (int i = 0; i < bombCharacters.length; i++) {
            if (stack.get(stack.size() - bombCharacters.length + i) != bombCharacters[i]) {
                return false;
            }
        }
        return true;
    }
}
