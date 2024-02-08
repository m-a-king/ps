package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BJ1991 {

    static StringBuilder stringBuilder = new StringBuilder();
    static Map<String, Node> nodes = new HashMap<>();

    private static class Node {
        String value;
        Node left, right;


        public Node(String value) {
            this.value = value;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());


        for (int i = 0; i < n; i++) {
            String[] clr = bufferedReader.readLine().split(" ");
            String current = clr[0];
            String left = clr[1];
            String right = clr[2];

            nodes.putIfAbsent(current, new Node(current));

            Node cNode = nodes.get(current);
            if (!left.equals(".")) {
                nodes.putIfAbsent(left, new Node(left));
                cNode.setLeft(nodes.get(left));
            }
            if (!right.equals(".")) {
                nodes.putIfAbsent(right, new Node(right));
                cNode.setRight(nodes.get(right));
            }
        }

        preorder(nodes.get("A"));
        stringBuilder.append("\n");
        inorder(nodes.get("A"));
        stringBuilder.append("\n");
        postorder(nodes.get("A"));
        System.out.println(stringBuilder.toString());
    }

    private static void postorder(Node current) {

        if (current == null) {
            return;
        }

        postorder(current.left);
        postorder(current.right);
        stringBuilder.append(current.value);
    }

    private static void inorder(Node current) {
        if (current == null) {
            return;
        }

        inorder(current.left);
        stringBuilder.append(current.value);
        inorder(current.right);


    }

    private static void preorder(Node current) {
        if (current == null) {
            return;
        }

        stringBuilder.append(current.value);
        preorder(current.left);
        preorder(current.right);

    }
}
