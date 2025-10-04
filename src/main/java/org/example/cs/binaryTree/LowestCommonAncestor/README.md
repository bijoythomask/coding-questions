# Lowest Common Ancestor of a Binary Tree

## Problem Statement
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree. The lowest common ancestor is defined as the lowest node in the tree that has both nodes as descendants (where a node can be a descendant of itself).

## Solution Approach
The solution uses a recursive approach to traverse the binary tree. Starting from the root, the algorithm checks if the current node is either of the target nodes. If so, it returns the current node. Otherwise, it recursively searches for the target nodes in the left and right subtrees.

- If both left and right recursive calls return non-null values, the current node is the LCA.
- If only one side returns a non-null value, that value is propagated up as the potential ancestor.
- If both sides return null, the current node is not part of the path to either target node.

This approach ensures that the first node where both targets are found in different subtrees is the lowest common ancestor.

## Complexity
- **Time Complexity:** O(N), where N is the number of nodes in the tree. Each node is visited once.
- **Space Complexity:** O(H), where H is the height of the tree due to recursion stack.

## Example
Given the following binary tree:

```
        3
      /   \
     5     1
    / \   / \
   6   2 0   8
      / \
     7   4
```
If the two nodes are 5 and 1, the LCA is 3.
If the two nodes are 5 and 4, the LCA is 5.

## Usage
Assuming you have a TreeNode class and the LowestCommonAncestor class with a method:

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
```
You can use it as follows:

```java
TreeNode lca = new LowestCommonAncestor().lowestCommonAncestor(root, node1, node2);
System.out.println("LCA: " + lca.val);
```

## File Location
- Source: LowestCommonAncestor.java

## License
See project-level license for usage and distribution terms.

