package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1686 {

    private static class Node {
        double x, y;
        int count;


        public Node(double x, double y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        int speed = Integer.parseInt(stringTokenizer.nextToken());
        int lifeTime = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        double sx = Double.parseDouble(stringTokenizer.nextToken());
        double sy = Double.parseDouble(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        double tx = Double.parseDouble(stringTokenizer.nextToken());
        double ty = Double.parseDouble(stringTokenizer.nextToken());

        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(sx, sy, 0));  // 시작점 추가

        String line;
        while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
            stringTokenizer = new StringTokenizer(line, " ");
            double x = Double.parseDouble(stringTokenizer.nextToken());
            double y = Double.parseDouble(stringTokenizer.nextToken());
            nodes.add(new Node(x, y, Integer.MAX_VALUE));  // 중간 지점 추가
        }
        nodes.add(new Node(tx, ty, Integer.MAX_VALUE));  // 종료점 추가

        double movableDis = speed * lifeTime * 60;
//        System.out.println("movableDis = " + movableDis);

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(nodes.get(0)); // 시작점 삽입

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.x == tx && curr.y == ty) {
                System.out.printf("Yes, visiting %d other holes.", curr.count-1);
                return;
            }

            for (Node next : nodes) {
                double distance = calculateDistance(curr.x, curr.y, next.x, next.y);
//                System.out.printf("Distance from (%.2f, %.2f) to (%.2f, %.2f) is %.2f\n", curr.x, curr.y, next.x, next.y, distance);
                if (distance <= movableDis) {
                    if(next.count != Integer.MAX_VALUE || next.count <= curr.count + 1) continue; // 재방문 체크
                    next.count = curr.count + 1;
                    queue.offer(next);
                }
            }
        }
        System.out.println("No.");


    }

    public static double calculateDistance(double x, double y, double x1, double y1) {
        return Math.sqrt(Math.pow(x1 - x, 2) + Math.pow(y1 - y, 2));
    }
}
