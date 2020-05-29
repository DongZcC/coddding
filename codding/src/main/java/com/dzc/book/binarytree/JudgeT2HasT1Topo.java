package com.dzc.book.binarytree;

import java.util.Stack;

/**
 * @author Administrator
 * @date 2020-05-29 16:14
 * <p>
 * 判断t1树是否包含t2树全部的拓扑结构
 */
public class JudgeT2HasT1Topo {


    public boolean t2Hast1Topo(Node t1, Node t2) {
        if (t1 == null || t2 == null) {
            return false;
        }

        // 找到t1, 和 t2 值相同的一个node
        Node cur = t1;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (cur.data == t2.data) {
                    break;
                }
                cur = cur.right;
            }
        }

        // 遍历一遍没有找到 直接返回false
        if (cur == null) {
            return false;
        }

        // 现在 cur 的指向和 t2指向相同
        return dfs(cur, t2);
//        return hasTopo(t1.left, t2, t1.data) && hasTopo(t1.right, t2, t1.data);
    }

    private boolean dfs(Node t1, Node t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null || t1.data != t2.data) {
            return false;
        }
        boolean left = dfs(t1.left, t2.left);
        boolean right = dfs(t1.right, t2.right);
        return left && right;
    }


    public boolean contains(Node t1, Node t2) {
        return check(t1, t2) || contains(t1.left, t2) || contains(t1.right, t2);
    }

    private boolean check(Node t1, Node t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null || t1.data != t2.data) {
            return false;
        }
        // 走到这里就是已经 t1 t2 节点相同了
        return check(t1.left, t2.left) && check(t1.right, t2.right);
    }
}
