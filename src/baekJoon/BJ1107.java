package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1107 {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String target = bufferedReader.readLine();
        int IntTarget = Integer.parseInt(target);
        int defaultChannel = 100;

        int ec = Integer.parseInt(bufferedReader.readLine());

        if (ec == 10) {
            System.out.println(Math.abs(IntTarget - defaultChannel));
            return;
        }

        boolean[] isBroken = new boolean[10];
        if (ec > 0) {
            String[] brokenChannels = bufferedReader.readLine().split(" ");
            for (String channel : brokenChannels) {
                int brokenChannel = Integer.parseInt(channel);
                isBroken[brokenChannel] = true;
            }
        }

        int minPress = Math.abs(IntTarget - defaultChannel);

        for (int channel = 0; channel <= 1000000; channel++) {
            if (isPossible(channel, isBroken)) {
                int press = Math.abs(IntTarget - channel) + numDigits(channel);
                minPress = Math.min(minPress, press);
            }
        }

        System.out.println(minPress);
    }

    private static boolean isPossible(int channel, boolean[] isBroken) {
        if (channel == 0) {
            return !isBroken[0];
        }

        while (channel > 0) {
            int digit = channel % 10;
            if (isBroken[digit]) {
                return false;
            }
            channel /= 10;
        }

        return true;
    }

    private static int numDigits(int channel) {
        if (channel == 0) {
            return 1;
        }

        int count = 0;
        while (channel > 0) {
            count++;
            channel /= 10;
        }
        return count;
    }
}
