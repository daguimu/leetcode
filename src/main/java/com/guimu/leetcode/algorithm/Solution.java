package com.guimu.leetcode.algorithm;
/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 * }
 */


public class Solution {

    /**
     * @param head ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // write code here
        ClipNode clip = clip(head, k);
        ListNode result = new ListNode();
        while (clip.pre != null) {
            clip.pre = getLength(clip.pre) == k ? reverse(clip.pre) : clip.pre;
            ListNode tailNode = getTailNode(result);
            tailNode.next = clip.pre;
            head = clip.tail;
            clip = clip(head, k);
        }
        return result.next;
    }

    public int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public ListNode getTailNode(ListNode head) {
        ListNode tail = head;
        while (head != null) {
            if (head.next == null) {
                tail = head;
            }
            head = head.next;
        }
        return tail;
    }


    public static ClipNode clip(ListNode head, int k) {
        k--;
        ListNode tail = head;
        while (tail != null && k > 0) {
            tail = tail.next;
            k--;
        }
        ClipNode clip = new ClipNode();
        clip.pre = head;
        clip.tail = tail != null ? tail.next : null;
        if (tail != null) {
            tail.next = null;
        }
        return clip;
    }


    // 反转链表
    public static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}

class ClipNode {

    ListNode pre;
    ListNode tail;
}