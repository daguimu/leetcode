package com.guimu.leetcode.algorithm;

import org.junit.jupiter.api.Test;

/**
 * @Description:
 * @Author: Guimu
 * @Create: 2019/04/09 20:32:20
 **/

public class BInarySearch {

    @Test
    public void test() {
        int[] arr = new int[]{1, 3, 4, 6, 7, 8, 14, 26, 78, 91,100};
        int resu = binaryBearch1(arr, 3);
        System.out.println(resu);
    }

    /**
     * @Author: Guimu
     * @Description: 二分查找返回索引
     * @Param:
     * @Return:
     * @Date: 2019-04-09 20:48
     */
    private int binaryBearch1(int[] arr, int k) {
        int index = 0;
        int low = 0;
        int len = arr.length;
        int high = len - 1;
        int mid = (low + high) >> 1;
        boolean flag = false;
        if (arr[low] == k || arr[high] == k) {
            index = arr[low] == k ? low : high;
            flag = true;
        }
        while (mid != 0 && !flag && (low + 1) != high) {

            if (arr[mid] > k) {
                high = mid;
            } else if (arr[mid] < k) {
                low = mid;
            } else {
                index = mid;
                flag = true;
                break;
            }
            mid = (low + high) >> 1;
        }
        return flag ? index : -1;
    }
}
