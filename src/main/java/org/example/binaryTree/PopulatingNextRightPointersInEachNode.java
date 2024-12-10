package org.example.binaryTree;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * Example 2:
 *
 * Input: root = []
 * Output: []
 */
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
