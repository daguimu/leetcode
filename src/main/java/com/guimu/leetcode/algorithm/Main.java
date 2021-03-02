/*
 * Copyright (c) 2021 maoyan.com
 * All rights reserved.
 *
 */
package com.guimu.leetcode.algorithm;

/**
 * @author Guimu
 * @date 2021/01/12
 */
public class Main {

    public static void main(String[] args) {

        Node1 head = init(0, 1, 5, 0, 9);
        printNode1(head);
        System.out.println("-------");

        ResNode resNode = filter(head);

        printNode1(resNode.first);

        printNode1(resNode.last);

    }

    public static ResNode filter(Node1 first) {
        Node1 pre = new Node1();
        Node1 res = pre;
        pre.next = first;
        Node1 cur = first;
        while (cur != null) {
            if (cur.value == 0) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        ResNode resNode = new ResNode();
        resNode.first = res.next;
        resNode.last = pre;
        return resNode;
    }

    public static Node1 init(int... arr) {
        Node1 head = new Node1();
        Node1 cur = head;
        int index = 0;
        while (index < arr.length) {
            Node1 newNode = new Node1();
            newNode.value = arr[index++];
            cur.next = newNode;
            cur = newNode;
        }
        return head.next;
    }


    public static void printNode1(Node1 head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

}


class ResNode {

    Node1 first;
    Node1 last;
}

class Node1 {

    int value;
    Node1 next;
}



