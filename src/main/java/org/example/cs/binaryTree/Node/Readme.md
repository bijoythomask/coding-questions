# Node Class for Binary Tree with Next Pointer

## Description

This file defines a `Node` class that is commonly used to represent a node in a binary tree. In addition to the standard `left` and `right` child pointers, this `Node` class includes a `next` pointer. This `next` pointer is often used in problems where you need to connect nodes at the same level in a binary tree, such as in the "Populating Next Right Pointers in Each Node" problem.

## Class Definition

```java
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
```

### Fields

*   `val`: The value of the node.
*   `left`: A pointer to the left child of the node.
*   `right`: A pointer to the right child of the node.
*   `next`: A pointer to the next node at the same level.
