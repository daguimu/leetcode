/*
 * Copyright (c) 2021 maoyan.com
 * All rights reserved.
 *
 */
package com.guimu.leetcode.algorithm;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * @author Guimu
 * @date 2021/01/11
 */
public class Test12 {

    @Test
    public void test1() {

        int[] a = {1, 3, 5, 7};
        int[] b = {2, 5, 9, 10,12,19};
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (j < b.length) {
            result[k++] = b[j++];
        }
        while (i < a.length) {
            result[k++] = a[i++];
        }
        System.out.println(Arrays.toString(result));

    }
}