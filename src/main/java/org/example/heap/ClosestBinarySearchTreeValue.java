package org.example.heap;
/**
 * Q: Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 * Note:
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 * Example:
 * Input: root = [4,2,5,1,3], target = 3.714286
 * Output: 4
 */
public class ClosestBinarySearchTreeValue {
    public int closestValue(TreeNode root, double target) {
        int closest = root.val;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - closest)) {
                closest = root.val;
            }
            root = root.val > target ? root.left : root.right;
        }
        return closest;
    }

    public static void main(String[] args) {
        ClosestBinarySearchTreeValue closestBinarySearchTreeValue = new ClosestBinarySearchTreeValue();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        double target = 2.1;
        System.out.println(closestBinarySearchTreeValue.closestValue(root, target));
    }
}
