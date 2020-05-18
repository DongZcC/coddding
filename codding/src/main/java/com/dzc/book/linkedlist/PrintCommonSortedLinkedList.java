package com.dzc.book.linkedlist;

import java.util.LinkedList;

/**
 * @author Administrator
 * @date 2020-05-18 17:06
 * <p>
 * 打印两个有序链表的公共部分
 */
public class PrintCommonSortedLinkedList {


    public void printTwoSortedLinkedListComPart(LinkedList<Integer> head1, LinkedList<Integer> head2) {
        // 这里直接用merge Sort 的一个思想把.
        while (!head1.isEmpty() && !head2.isEmpty()) {
            if (head1.getFirst() < head2.getFirst()) {
                head1.removeFirst();
            } else if (head1.getFirst() > head2.getFirst()) {
                head2.removeFirst();
            } else {
                System.out.println(head1.removeFirst());
                head2.removeFirst();
            }
        }
    }


    public static void main(String[] args) {
        PrintCommonSortedLinkedList print = new PrintCommonSortedLinkedList();
        LinkedList<Integer> h1 = new LinkedList<>();
        h1.add(1);
        h1.add(2);
        h1.add(3);
        h1.add(4);
        h1.add(5);


        LinkedList<Integer> h2 = new LinkedList<>();
        h2.add(3);
        h2.add(4);
        h2.add(5);
        h2.add(6);
        h2.add(7);
        h2.add(8);
        h2.add(9);
        print.printTwoSortedLinkedListComPart(h1, h2);
    }
}
