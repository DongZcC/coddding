package com.dzc.book.binarytree;

import java.util.UUID;

/**
 * @author Administrator
 * @date 2020-06-01 16:07
 * <p>
 * Tarjan算法 与 并查集解决二叉树节点间最近公共祖先的批量查询问题
 */
public class Tarjan {

    class Query{
        public Node o1;
        public Node o2;

        public Query(Node o1, Node o2) {
            this.o1 = o1;
            this.o2 = o2;
        }
    }

    public static void main(String[] args) {
        String s = System.getProperty("os.name").toLowerCase();
        System.out.println(s);
        UUID.randomUUID();
    }


    public Node[] findPubAncestor(Query[] queries) {
        return null;
    }
}
