package com.Q_Four;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Four_BTest {

    @Test
    void closestKValues_Test1() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        double target = 3.8;
        int k = 2;

        List<Integer> expected = List.of(3, 4);
        List<Integer> result = Four_B.closestKValues(root, target, k);

        assertEquals(expected, result);
    }

    @Test
    void closestKValues_Test2() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);

        double target = 5.2;
        int k = 3;

        List<Integer> expected = List.of(4, 5, 7);
        List<Integer> result = Four_B.closestKValues(root, target, k);

        assertEquals(expected, result);
    }

    @Test
    void closestKValues_Test3() {
        TreeNode root = new TreeNode(5);

        double target = 5.0;
        int k = 1;

        List<Integer> expected = List.of(5);
        List<Integer> result = Four_B.closestKValues(root, target, k);

        assertEquals(expected, result);
    }

    @Test
    void closestKValues_Test4() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        double target = 4.0;
        int k = 2;

        List<Integer> expected = List.of(3, 2);//Should return (2,3) as the result is sorted in ascending order
        List<Integer> result = Four_B.closestKValues(root, target, k);

        assertNotEquals(expected,result);
    }
}
