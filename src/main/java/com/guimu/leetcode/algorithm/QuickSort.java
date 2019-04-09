package com.guimu.leetcode.algorithm;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * @Description: 快排
 * @Author: Guimu
 * @Create: 2019/04/07 10:41:32
 **/

public class QuickSort {

    @Test
    public void test() {
        int[] arr = {1, 6, 2, 5, 9, 4};
//        insertSort(arr);
        quickSort(arr, 0, 5);
        System.out.println(Arrays.toString(arr));

    }

    private void quickSort(int[] arr, int low, int high) {
        int i = low;
        int j = high;
        while (i < j) {
            int pov = arr[low];

            while (i < j && arr[j] >= pov) {
                j--;
            }
            if (i < j) {
                arr[i++] = arr[j];
            }
            while (i < j && arr[i] <= pov) {
                i++;
            }
            if (i < j) {
                arr[j--] = arr[i];
            }
            arr[i] = pov;
            quickSort(arr, low, i - 1);
            quickSort(arr, i + 1, high);
        }
    }

    /**
     * @Author: Guimu
     * @Description: 插入排序
     * @Param:
     * @Return:
     * @Date: 2019-04-07 10:57
     */
    private void insertSort(int... arr) {
        for (int j, i = 1; i < arr.length; i++) {
            int cur = arr[i];
            for (j = i - 1; j >= 0 && cur < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = cur;
        }
    }
}
