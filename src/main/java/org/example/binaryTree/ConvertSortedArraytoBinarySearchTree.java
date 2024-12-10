package org.example.binaryTree;

/**
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a
 * height-balanced
 *  binary search tree.
 * Example 1:
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 *
 * Example 2:
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 */
public class ConvertSortedArraytoBinarySearchTree {
    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = helper(nums,0, nums.length - 1);
        inOrderTranversal(root);
    }

    public static void inOrderTranversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTranversal(root.left);
        System.out.print( " " + root.val);
        inOrderTranversal(root.right);
    }



    private static TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }
}
