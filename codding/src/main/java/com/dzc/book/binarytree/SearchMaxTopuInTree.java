package com.dzc.book.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020-05-28 14:50
 * 找到二叉树中符合搜索二叉树条件的最大拓扑结构大小
 */
public class SearchMaxTopuInTree {

    public int bstTopoSize(Node head) {
        if (head == null) {
            return 0;
        }
        int max = maxTopo(head, head);
        max = Math.max(max, bstTopoSize(head.left));
        max = Math.max(max, bstTopoSize(head.right));
        return max;
    }

    private int maxTopo(Node h, Node n) {
        if (h != null && n != null && isBSTNode(h, n, n.data)) {
            return maxTopo(h, n.left) + maxTopo(h, n.right) + 1;
        }
        return 0;
    }


    /**
     * 根据一个节点贡献记录， 保存二叉树每个节点能正常topo 的一个Record值
     * <p>
     * 后续遍历二叉树， 逐级遍历， 找到最大的值
     *
     * @param head
     * @return
     */
    public int bstTopoSize2(Node head) {
        if (head == null) {
            return 0;
        }
        Map<Node, Record> map = new HashMap<>();
        return postOrder(head, map);
    }

    class Record {
        int leftValue;
        int rightValue;

        public Record(int leftValue, int rightValue) {
            this.leftValue = leftValue;
            this.rightValue = rightValue;
        }
    }

    private int postOrder(Node node, Map<Node, Record> map) {
        if (node == null) {
            return 0;
        }

        // 左子树的一个最大贡献记录
        int ls = postOrder(node.left, map);

        // 右子树的一个最大贡献记录
        int rs = postOrder(node.right, map);

        // 开始计算当前节点的最大贡献记录
        modifyMap(node.left, node.data, map, true);
        modifyMap(node.right, node.data, map, false);

        Record lr = map.get(node.left);
        Record rr = map.get(node.right);

        int lbst = lr == null ? 0 : lr.leftValue + 1 + lr.rightValue;
        int rbst = rr == null ? 0 : rr.leftValue + 1 + rr.rightValue;

        map.put(node, new Record(lbst, rbst));
        return Math.max(lbst + rbst + 1, Math.max(ls, rs));
    }

    private int modifyMap(Node n, int data, Map<Node, Record> map, boolean s) {
        if (n == null || !map.containsKey(n)) {
            return 0;
        }
        Record r = map.get(n);

        if (s && n.data > data || (!s && n.data < data)) {
            map.remove(n);
            return r.leftValue + r.rightValue + 1;
        } else {
            int minus = modifyMap(s ? n.right : n.left, data, map, s);
            if (s) {
                r.rightValue = r.rightValue - minus;
            } else {
                r.leftValue = r.leftValue - minus;
            }
            map.put(n, r);
            return minus;
        }

    }


    private boolean isBSTNode(Node h, Node n, int data) {
        if (h == null) {
            return false;
        }
        if (h == n) {
            return true;
        }
        return isBSTNode(h.data > data ? h.left : h.right, n, data);
    }


    public static void main(String[] args) {
        Node root = new Node(-3);
        Node node3 = new Node(3);
        Node node1 = new Node(1);
        Node node0 = new Node(0);
        Node node01 = new Node(1);
        Node node6 = new Node(6);
        Node node99 = new Node(-9);
        Node node2 = new Node(2);
        Node node11 = new Node(1);


        root.left = node3;
        root.left = node99;
        node3.left = node1;
        node3.right = node0;
        node0.left = node01;
        node0.right = node6;
        node99.left = node2;
        node99.right = node11;


        SearchMaxTopuInTree sear = new SearchMaxTopuInTree();
        int i = sear.bstTopoSize2(root);
        System.out.println(i);
    }
}
