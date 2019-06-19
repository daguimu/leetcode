package com.guimu.leetcode.algorithm;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @Description: 链表反转
 * @Author: Guimu
 * @Create: 2019/04/06 12:32:59
 **/

public class LinkReverse {

    @Test
    public void test() {
        Node root = create(Arrays.asList(1, 5, 3, 4));
        root = reverseLink(root);
        System.out.println(root);
    }

    @Test
    public void swap() {
        Node root = create(Arrays.asList(1, 2, 3, 4));
        root = swapNode(root);
        System.out.println(root);
    }

    /**
     * @Author: Guimu
     * @Description: 单链表的相邻节点进行交换,
     * @Param:
     * @Return:
     * @Date: 2019-04-23 14:56
     */

    private Node swapNode(Node cur) {
        return null;
    }

    /**
     * @Author: Guimu
     * @Description: 单链表反转
     * @Param:
     * @Return:
     * @Date: 2019-04-06 13:55
     */
    private Node reverseLink(Node link) {
        Node head = null;
        Node pre = null;
        while (link != null) {
            Node next = link.getNext();
            if (next == null) {
                head = link;
            }
            link.setNext(pre);
            pre = link;
            link = next;
        }
        return head;
    }

    /**
     * @Author: Guimu
     * @Description: 基于list创建一个单向链表
     * @Param:
     * @Return:
     * @Date: 2019-04-06 14:40
     */
    public Node create(List<Integer> list) {
        Node root = new Node();
        Node cur = root;
        for (Integer val : list) {
            if (cur.getVal() == null) {
                cur.setVal(val);
            } else {
                Node temp = new Node();
                temp.setVal(val);
                cur.setNext(temp);
                cur = temp;
            }
        }
        return root;
    }

}


class Node {

    private Node next;
    private Integer val;

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}
