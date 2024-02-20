package com.Q_Four;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class Four_B {
    public static List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> predecessorStack = new Stack<>();
        Stack<Integer> successorStack = new Stack<>();

        // Initialize both stacks during the in-order traversal
        inorderTraversal(root, target, false, predecessorStack);
        inorderTraversal(root, target, true, successorStack);

        // Merge the stacks to find the k closest values
        while (k-- > 0) {
            if (predecessorStack.isEmpty()) {
                result.add(successorStack.pop());
            } else if (successorStack.isEmpty()) {
                result.add(predecessorStack.pop());
            } else if (Math.abs(predecessorStack.peek() - target) < Math.abs(successorStack.peek() - target)) {
                result.add(predecessorStack.pop());
            } else {
                result.add(successorStack.pop());
            }
        }
        Collections.sort(result); // Sort the result list

        return result;
    }

    // perform in-order traversal of the BST
    private static void inorderTraversal(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> nodeStack = new Stack<>();
        TreeNode current = root;

        // Traverse the tree in-order using a stack
        while (current != null || !nodeStack.isEmpty()) {
            while (current != null) {
                nodeStack.push(current);
                current = (reverse) ? current.right : current.left; // Traverse right or left based on direction
            }

            current = nodeStack.pop(); // Pop the node from the stack

            // Break traversal if the target direction is reached
            if (!reverse && current.val > target) {
                break;
            }
            if (reverse && current.val <= target) {
                break;
            }

            stack.push(current.val); // Add the node value to the stack

            current = (reverse) ? current.left : current.right; // Move to the next node based on direction
        }
    }
    public static void main(String[] args) {
        // Create a sample binary search tree
        //          4
        //        /   \
        //       2     5
        //      / \     \
        //     1   3     6
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(6);

        
        double target = 3.5;
        int k = 3;

        
        List<Integer> closestValues = Four_B.closestKValues(root, target, k);

       
        System.out.println("Closest " + k + " values to " + target + ": " + closestValues);
    }
}

