/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.guimu.leetcode.algorithm;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import org.junit.jupiter.api.Test;

/**
 * 239 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。  返回滑动窗口最大值。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Guimu
 * @created: 2019/06/18
 */
public class SlidingWin {

    private int[] getSliding(int[] arr, int k) {
        if (arr.length == 0) {
            return new int[0];
        }
        int[] resu = new int[arr.length - k + 1];
        int indc = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            insert(deque, arr, i, k);
            //只能在窗口范围达到k之后才进行存储,初始的时候窗口的范围是0,还不到k所以队首元素不是当前窗口的最大值,即前0 ~~ k-1不进行最大值数据存储
            if (k <= i + 1) {
                resu[indc++] = arr[deque.peekFirst()];
            }
        }
        return resu;
    }

    private void insert(Deque<Integer> deque, int[] arr, int ele, int k) {
        // 删除队列中比ele索引对应数字小的所有数据
        if (deque.size() > 0) {
            deque.removeIf(el -> arr[el] < arr[ele]);
        }
        // 如果队首元素不在窗口范围内,需要删除
        if (!deque.isEmpty() && (ele - deque.peekFirst() == k)) {
            deque.pollFirst();
        }
        deque.addLast(ele);
    }

    @Test
    public void test() {
        int[] arr = new int[]{1, 3, -1, -3, 2, -7};
        int k = 3;
        System.out.println(Arrays.toString(getSliding(arr, k)));
    }
}