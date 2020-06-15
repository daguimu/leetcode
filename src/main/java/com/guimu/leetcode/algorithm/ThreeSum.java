/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.guimu.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * 15 题给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/3sum-closest 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Guimu
 * @date 2019/06/17
 */
public class ThreeSum {

    public List<List<Integer>> twoSumTest(int[] arr, int target) {
        if (arr.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> resu = new ArrayList<>();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 2; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            int r = arr.length - 1;
            for (int l = i + 1; l < r; ) {
                int sum = arr[l] + arr[r] + arr[i] - target;
                if (sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    List<Integer> result = new ArrayList<>();
                    result.add(arr[l]);
                    result.add(arr[r]);
                    result.add(arr[i]);
                    resu.add(result);
                    while (l < r && arr[l] == arr[l + 1]) {
                        l++;
                    }
                    while (l < r && arr[r] == arr[r - 1]) {
                        r--;
                    }
                    r--;
                    l++;
                }
            }

        }
        return resu;
    }


    @Test
    public void test() {
        int[] arr = {-2, 0, 1, 1, 2};
        int target = 0;
        List<List<Integer>> arr1 = this.twoSumTest(arr, target);
        for (int i = 0; i < arr1.size(); i++) {
            System.out.println(Collections.singletonList(arr1.get(i)));
        }
    }
}