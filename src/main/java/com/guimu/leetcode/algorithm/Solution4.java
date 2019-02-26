package com.guimu.leetcode.algorithm;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * @Description: 寻找两个有序数组的中位数
 * @Author: Guimu
 * @Create: 2019/02/25 15:16:07
 **/

public class Solution4 {

    private double ty(int[] arr1, int[] arr2) {
        if (arr1.length > arr2.length) {
            int[] tempa = arr1;
            arr1 = arr2;
            arr2 = tempa;
        }
        int low = 0;
        int high = 0;
        int lowLen = arr1.length;
        int highLen = arr2.length;
        int[] restlt = new int[arr1.length + arr2.length];
        int index = 0;
        for (; low < lowLen && high < highLen; ) {
            if (arr1[low] < arr2[high]) {
                restlt[index++] = arr1[low++];
            } else if (arr1[low] > arr2[high]) {
                restlt[index++] = arr2[high++];
            } else {
                restlt[index++] = arr1[low++];
                restlt[index++] = arr2[high++];
            }
        }
        if (low == lowLen) {
            deal(arr2, restlt, high, index);
        } else {
            deal(arr1, restlt, low, index);
        }
        double myRe;
        if (restlt.length % 2 == 0) {
            myRe = (restlt[restlt.length / 2] + restlt[restlt.length / 2 - 1]) / 2.0;
        } else {
            myRe = restlt[restlt.length / 2];

        }
        System.out.println(Arrays.toString(restlt));
        return myRe;
    }


    private void deal(int[] arr, int[] result, int from, int cur) {
        for (int i = 0; i < arr.length; i++) {
            if (i >= from) {
                result[cur++] = arr[i];
            }
        }
    }

    /**
     * @Author: Guimu
     * @Description: 寻找两个有序数组的中位数
     * @Param: [nums1, nums2]
     * @Return: double
     * @Date: 2019-02-25 15:17
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return ty(nums1, nums2);
    }



    @Test
    public void io() {
        int[] arr1 = new int[]{-1, -2};
        int[] arr2 = new int[]{-3};
        System.out.println(ty(arr1, arr2));
    }
}
