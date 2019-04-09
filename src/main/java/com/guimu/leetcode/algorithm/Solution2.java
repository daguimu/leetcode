package com.guimu.leetcode.algorithm;

import org.junit.jupiter.api.Test;

/**
 * @Description: 两数相加
 * @Author: Guimu
 * @Create: 2019/02/28 22:50:31
 **/

public class Solution2 {

    @Test
    public void get() {
        ListNode l1 = conventor("36");
        ListNode l2 = conventor("2");
        System.out.println(addTwoNumbers(l1, l2));
    }


    private String addStr(String s1, String s2) {
        StringBuilder builder = new StringBuilder();
        int index = s1.length() - 1;
        boolean flag = false;
        while (index >= 0) {
            int teep = flag ? 1 : 0;
            int temp = s1.charAt(index) + s2.charAt(index) - 96 + teep;
            flag = temp >= 10;
            builder.append(temp % 10);
            index--;
        }
        if (flag) {
            builder.append(1);
        }
        builder.reverse();
        return builder.toString();
    }

    public ListNode addTwoNumbers(ListNode cur1, ListNode cur2) {
        ListNode sum = null;
        ListNode ever = null;
        boolean isTeep = false;
        while (cur1 != null || cur2 != null) {
            int tempSum = isTeep ? 1 : 0;
            if (cur1 != null) {
                tempSum += cur1.val;
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                tempSum += cur2.val;
                cur2 = cur2.next;
            }
            isTeep = tempSum >= 10;
            ListNode bit = new ListNode(tempSum % 10);
            if (sum == null) {
                sum = bit;
            } else {
                ever.next = bit;
            }
            ever = bit;
        }
        if (isTeep) {
            ever.next = new ListNode(1);
        }
        return sum;
    }

    private ListNode conventor(String num) {
        char[] chars = new StringBuilder(num).reverse().toString().toCharArray();
        ListNode head = new ListNode(chars[0] - 48);
        ListNode cur = head;
        for (int i = 1; i < chars.length; i++) {
            char ch = chars[i];
            ListNode temp = new ListNode(ch - 48);
            cur.next = temp;
            cur = temp;
        }
        return head;
    }


    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


}
