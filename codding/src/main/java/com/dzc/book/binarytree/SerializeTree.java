package com.dzc.book.binarytree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Administrator
 * @date 2020-05-26 13:46
 * <p>
 * 序列化和反序列化一颗树.
 * <p>
 * 先序遍历， 树 ；
 * <p>
 * 反序列化的时候 用一个队列， 先入先出. 分别是左child  右child 的方式
 */
public class SerializeTree {

    /**
     * 先序遍历 序列化一颗树；
     * 如果遇到null节点， 则加上 #!
     *
     * @param root
     * @return
     */
    public String serializeTree(Node root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    public Node reconByString(String preString) {
        String[] value = preString.split("!");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < value.length; i++) {
            queue.offer(value[i]);
        }
        return reconPreOrder(queue);
    }

    private Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

    private void preOrder(Node node, StringBuilder sb) {
        if (node == null) {
            sb.append("#!");
            return;
        }
        sb.append(node.data + "!");
        preOrder(node.left, sb);
        preOrder(node.right, sb);
    }


    /**
     * 层级遍历二叉树.
     * 用一个队列。 广度优先搜索
     *
     * @param root
     * @return
     */
    public String serializeTree2(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur == null) {
                sb.append("#!");
            } else {
                sb.append(cur.data + "!");
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return sb.toString();
    }

    public Node reconByString2(String levelString) {
        String[] values = levelString.split("!");
        int index = 0;
        Node head = generateNodeByString(values[index++]);
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.offer(head);
        }

        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNodeByString(values[index++]);
            node.right = generateNodeByString(values[index++]);

            if (node.left != null)
                queue.offer(node.left);

            if (node.right != null)
                queue.offer(node.right);
        }
        return head;
    }

    private Node generateNodeByString(String value) {
        if (value.equals("#")) {
            return null;
        }
        return new Node(Integer.valueOf(value));
    }


    public static void main(String[] args) {
        SerializeTree serializeTree = new SerializeTree();

        Node root1 = new Node(1);
        Node node2 = new Node(2);
        root1.left = node2;
        root1.right = new Node(3);
        node2.left = new Node(4);

        String serialize = serializeTree.serializeTree(root1);
        System.out.println(serialize);
        Node node = serializeTree.reconByString(serialize);
        System.out.println(node);

        String s = serializeTree.serializeTree2(root1);
        System.out.println(s);

        Node node1 = serializeTree.reconByString2(s);
        System.out.println(node1);
    }
}
