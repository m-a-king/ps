package baekJoon;

import java.time.LocalDateTime;

public class BJ10699 {

    public static void main(String[] args) {

        String s = LocalDateTime.now().toString().split("T")[0];

        System.out.println(s);

    }
}
