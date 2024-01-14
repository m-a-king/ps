package baekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class BJ5639 {

    static class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public void insert(int newValue) {
            if (newValue < this.value) {
                if (this.left == null) {
                    this.left = new Node(newValue);
                } else {
                    this.left.insert(newValue);
                }
            } else if (newValue > this.value) {
                if (this.right == null) {
                    this.right = new Node(newValue);
                } else {
                    this.right.insert(newValue);
                }
            }
        }
    }

    static Node root = null;

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while ((input = bufferedReader.readLine()) != null && !input.isEmpty()) {
            int num = parseInt(input);

            if (root == null) {
                root = new Node(num);
            } else {
                root.insert(num);
            }

        }

        postOrder(root);

    }

    public static void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left); // 왼쪽 서브트리 순회
            postOrder(node.right); // 오른쪽 서브트리 순회
            System.out.println(node.value); // 현재 노드 처리
        }
    }
}
