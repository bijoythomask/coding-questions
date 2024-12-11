package org.example.cs.stack;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }
    public TreeNode(int x) {
        val = x;
    }
    @Override
    public String toString() {
        return "Value: " + val ;
    }
}
