/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.guimu.leetcode.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific
 * target.  You may assume that each input would have exactly one solution, and you may not use the
 * same element twice.
 *
 * @author: Guimu
 * @created: 2019/06/17
 */
public class TwoSum {

    public int[] twoSumTest(int[] arr, int target) {
        int[] result = new int[2];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            int el = arr[i];
            if (set.contains(target - el)) {
                result[1] = i;
                result[0] = this.getIndex(arr, target - el);
            } else {
                set.add(el);
            }
        }
        return result;
    }

    private int getIndex(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[] arr = {3, 2, 4};
        int target = 6;
        System.out.println(Arrays.toString(this.twoSumTest(arr, target)));
    }
}