package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class kTest1 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int m = Integer.parseInt(bufferedReader.readLine());
        List<List<Integer>> logs = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            logs.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int serverId = Integer.parseInt(stringTokenizer.nextToken());
            int time = Integer.parseInt(stringTokenizer.nextToken());
            logs.get(serverId).add(time);
        }
        int x = Integer.parseInt(bufferedReader.readLine());
        int q = Integer.parseInt(bufferedReader.readLine());
        int[] queries = new int[q];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        for (int i = 0; i < q; i++) {
            queries[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for (List<Integer> log : logs) {
            log.sort(Comparator.naturalOrder());
        }

        for (int i = 1; i <= n; i++) {
            System.out.println("i = " + i);
            for (int log : logs.get(i)) {
                System.out.print(log + " ");
            }
            System.out.println();
        }

        int[] answer = new int[q]; // 0->1, 1->2, ...
        Arrays.fill(answer, n);

        int queryIndex = 0;
        for (int query : queries) {
            System.out.println();
//            System.out.println("쿼리번호 = " + queryIndex);
            int timeStart = query - x; // 혹은 logs 리스트에서 직접 빼기
            int timeEnd = query;


            for (int i = 1; i <= n; i++) {
                List<Integer> currLogs = logs.get(i);
//                System.out.println("서버아이디 = " + i);

                int start = 0;
                int end = currLogs.size() - 1;

                while (start <= end) {
                    int mid = start + end;
                    // int mid = start + (end - start) / 2;

                    int currTime = currLogs.get(mid);
//                    System.out.println("currTime = " + currTime);
                    if (timeStart <= currTime && currTime <= timeEnd) {
                        answer[queryIndex]--;
//                        System.out.println("나는 통과");
                        break;
                    }
                    if (currTime < timeStart) {
                        start = mid + 1;
//                        System.out.println("큰값찾기");
                        continue;
                    }
                    if (currTime > timeEnd) {
                        end = mid-1;
//                        System.out.println("작은값찾기");
                    }


                }
            }
            queryIndex++;
        }

        System.out.println("answer");
        for (int a : answer) {
            System.out.println(a);
        }

    }

}


// 1 3 5 7 9
// 5 ~ 10

// -4 -2 0 2 4
// 0 ~ 5