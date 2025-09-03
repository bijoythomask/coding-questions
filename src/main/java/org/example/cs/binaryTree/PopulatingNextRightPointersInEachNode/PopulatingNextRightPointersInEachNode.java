package org.example.cs.binaryTree.PopulatingNextRightPointersInEachNode;

import org.example.cs.binaryTree.Node.*;

public class PopulatingNextRightPointersInEachNode {
    public Node connect(Node root) {
        if (root == null) return null;
        connect(root.left, root.right);
        return root;
    }
    private void connect(Node left, Node right) {
        if (left == null || right == null) return;
        left.next = right;
        connect(left.left, left.right);
        connect(right.left, right.right);
        connect(left.right, right.left);
    }
    public static void printInOrder(Node root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.println(root.val + " " + root.next);
        printInOrder(root.right);
    }
    public static void main(String[] args) {
        PopulatingNextRightPointersInEachNode populatingNextRightPointersInEachNode = new PopulatingNextRightPointersInEachNode();
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        populatingNextRightPointersInEachNode.connect(root);
        printInOrder(root);
    }
}
