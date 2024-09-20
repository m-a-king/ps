package programmers;

public class 동영상재생기 {

    public static void main(String[] args) {

        System.out.println(solution("34:33", "13:00", "00:55", "02:55", new String[]{"next", "prev"}));

    }

    public static String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";

        String[] v = video_len.split(":");
        String[] p = pos.split(":");
        String[] s = op_start.split(":");
        String[] e = op_end.split(":");

        int limit = Integer.parseInt(v[0]) * 60 + Integer.parseInt(v[1]);
        int curr = Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
        int ops = Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
        int ope = Integer.parseInt(e[0]) * 60 + Integer.parseInt(e[1]);

        for (String command : commands) {
            if (ops <= curr && curr <= ope) {
                curr = ope;
            }

            if (command.startsWith("n")) {
                curr += 10;
                if (curr > limit) {
                    curr = limit;
                }
            } else {
                curr -= 10;
                if (curr < 0) {
                    curr = 0;
                }
            }
        }

        if (ops <= curr && curr <= ope) {
            curr = ope;
        }

        answer = curr / 60 < 10 ? 0 + "" + curr / 60 : curr / 60 + "";
        answer += ":";
        answer += curr % 60 < 10 ? 0 + "" + curr % 60 : curr % 60;


        return answer;
    }
}
