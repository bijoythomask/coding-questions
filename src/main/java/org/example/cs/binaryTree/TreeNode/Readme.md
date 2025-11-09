# TreeNode Class for Binary Tree

## Description

This file defines a `TreeNode` class, a fundamental building block for representing nodes in a binary tree. It includes the node's value (`val`) and pointers to its left and right children (`left` and `right`).

## Class Definition

```java
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }
    public TreeNode(int x) {
        val = x;
    }

   public void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }
}
```

### Fields

*   `val`: The integer value stored in the node.
*   `left`: A reference to the left child `TreeNode`.
*   `right`: A reference to the right child `TreeNode`.

### Methods

*   `printInOrder(TreeNode root)`: A utility function to print the nodes of a binary tree in-order.
