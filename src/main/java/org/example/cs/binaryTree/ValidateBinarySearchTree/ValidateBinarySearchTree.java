package org.example.cs.binaryTree.ValidateBinarySearchTree;

import org.example.cs.binaryTree.TreeNode.TreeNode;

public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }
    public static void main(String[] args) {
        ValidateBinarySearchTree validator = new ValidateBinarySearchTree();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(validator.isValidBST(root)); // true
    }
}
