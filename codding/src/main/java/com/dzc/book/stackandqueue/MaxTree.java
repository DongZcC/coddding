package com.dzc.book.stackandqueue;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created on 2020/5/17.
 * <p>
 * 构造数组的maxTree
 *
 * @author _Shrimp
 */
public class MaxTree {

    public static void main(String[] args) {
        MaxTree maxTree = new MaxTree();
        Node maxTree1 = maxTree.getMaxTree(new int[]{3, 4, 5, 1, 2});
        System.out.println(maxTree1);
    }

    /**
     * @param arr 没有重复元素的数组
     * @return 二叉树，数组的每一个值对应一个二叉树的节点.
     */
    public Node getMaxTree(int[] arr) {
        // 每一个树中。 最大值都是树的头.
        // 二叉树
        // arr  = [3,4,5,1,2]
        // root = 5
        // 3 -> 4 ->5 <- 2 <- 1
        Node head = null;

        Node[] nodes = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i]);
        }

        Stack<Node> stack = new Stack<>();
        HashMap<Node, Node> lBigMap = new HashMap<>();
        HashMap<Node, Node> rBigMap = new HashMap<>();

        // 找到当前节点左边第一个比当前节点大的值.
        for (int i = 0; i < nodes.length; i++) {
            Node curNode = nodes[i];
            while (!stack.isEmpty() && stack.peek().value < curNode.value) {
                popStackSetMap(stack, lBigMap);
            }
            stack.push(curNode);
        }

        while (!stack.isEmpty()) {
            popStackSetMap(stack, lBigMap);
        }


        for (int i = nodes.length - 1; i >= 0; i--) {
            Node curNode = nodes[i];
            while (!stack.isEmpty() && stack.peek().value < curNode.value) {
                popStackSetMap(stack, rBigMap);
            }
            stack.push(curNode);
        }

        // 处理队列中的剩余元素
        while (!stack.isEmpty()) {
            popStackSetMap(stack, rBigMap);
        }

        /// 开始构造树节点.
        for (int i = 0; i < nodes.length; i++) {
            Node curNode = nodes[i];
            Node left = lBigMap.get(curNode);
            Node right = rBigMap.get(curNode);

            if (left == null && right == null) {
                head = curNode;
            } else if (left == null) {
                if (right.left == null) {
                    right.left = curNode;
                } else {
                    right.right = curNode;
                }
            } else if (right == null) {
                if (left.left == null) {
                    left.left = curNode;
                } else {
                    left.right = curNode;
                }
            } else {
                Node parent = left.value > right.value ? right : left;
                if (parent.left == null) {
                    parent.left = curNode;
                } else {
                    parent.right = curNode;
                }
            }
        }

        return head;
    }

    void popStackSetMap(Stack<Node> stack, HashMap<Node, Node> map) {
        Node popNode = stack.pop();
        if (stack.isEmpty()) {
            map.put(popNode, null);
        } else {
            map.put(popNode, stack.peek());
        }
    }

}
