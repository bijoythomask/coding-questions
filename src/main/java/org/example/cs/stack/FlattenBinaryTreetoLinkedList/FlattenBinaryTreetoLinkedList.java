package org.example.cs.stack;

public class FlattenBinaryTreetoLinkedList {
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                TreeNode rightMost = root.left;
                while (rightMost.right != null) {
                    rightMost = rightMost.right;
                }
                rightMost.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        new FlattenBinaryTreetoLinkedList().flatten(root);
        while (root != null) {
            System.out.print(root.val + " ");
            root = root.right;
        }
    }
}
