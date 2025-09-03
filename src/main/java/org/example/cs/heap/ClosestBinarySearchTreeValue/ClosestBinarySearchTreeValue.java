package org.example.cs.heap.ClosestBinarySearchTreeValue;

import org.example.cs.binaryTree.TreeNode.TreeNode;

public class ClosestBinarySearchTreeValue {
    public int closestValue(TreeNode root, double target) {
        int closest = root.val;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - closest)) {
                closest = root.val;
            }
            root = target < root.val ? root.left : root.right;
        }
        return closest;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        ClosestBinarySearchTreeValue solver = new ClosestBinarySearchTreeValue();
        System.out.println(solver.closestValue(root, 3.714286)); // Output: 4
    }
}
