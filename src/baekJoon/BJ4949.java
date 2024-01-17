package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Stack;

public class BJ4949 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            String s = bufferedReader.readLine();
            if (s.equals(".")) {
                break;
            }

            String[] split = s.split("");
            Stack<String> stack = new Stack<>();

            int smallStart = 0;
            int smallEnd = 0;
            int bigStart = 0;
            int bigEnd = 0;
            int flag = 0;

            for (String sp : split) {

                if (sp.equals("(")) {
                    stack.push(sp);
                    smallStart++;
                }

                else if (sp.equals(")")) {
                    String pop = stack.isEmpty() ? null : stack.pop();

                    if (pop != null && !pop.equals("(")) {
                        flag = 1;

                        break;
                    }
                    smallEnd++;

                }

                else if (sp.equals("[")) {
                    stack.push(sp);
                    bigStart++;

                }

                else if (sp.equals("]")) {
                    String pop = stack.isEmpty() ? null : stack.pop();

                    if (pop != null && !pop.equals("[")) {
                        flag = 1;

                        break;
                    }
                    bigEnd++;

                }

                if (smallStart < smallEnd || bigStart < bigEnd) {
                    flag = 1;
                    break;
                }
            }

            if (stack.isEmpty() && flag == 0 && smallStart == smallEnd && bigStart == bigEnd) {
                System.out.println("yes");

            } else {
                System.out.println("no");
            }

        }
    }
}
