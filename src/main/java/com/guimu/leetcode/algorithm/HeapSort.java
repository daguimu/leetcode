package com.guimu.leetcode.algorithm;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

/**
 * @author Guimu
 * @date 2019/02/25 12:43:57
 **/

public class HeapSort {

    @Test
    public void te() {
        int[] arr = {1, 6, 2, 8, 4};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void rank() {
        int[] arr = {5, 2, 7, 3, 9, 6, 4};
        buildMaxHeap(arr, 3);
        for (int i = 3; i < arr.length; i++) {
            if (arr[i] > arr[0]) {
                swap(arr, 0, i);
                heapApiFy(arr, 0, 3);
            }
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(arr[i]);
        }
    }

    private void buildMaxHeap(int[] arr, int size) {
        Stream.iterate((size >> 1) - 1, x -> x - 1)
            .limit((size >> 1))
            .forEach(el -> heapApiFy(arr, el, size));
    }

    private void heapSort(int... arr) {
        buildMaxHeap(arr, arr.length);
        for (int i = arr.length - 1, len = arr.length; i > 0; i--) {
            swap(arr, 0, i);
            heapApiFy(arr, 0, --len);
        }
    }

    /**
     * 构建最大堆 使用 > 构建最小堆 使用 <
     *
     * @author Guimu
     * @date 2019-06-17
     */
    public void heapApiFy(int[] arr, int index, int size) {
        int l = (index << 1) + 1;
        int r = l + 1;
        int lagest = index;
        if (l < size && arr[l] < arr[lagest]) {
            lagest = l;
        }
        if (r < size && arr[r] < arr[lagest]) {
            lagest = r;
        }
        if (lagest != index) {
            swap(arr, lagest, index);
            heapApiFy(arr, lagest, size);
        }
    }

    /**
     * 返回该数组中的第k大元素
     *
     * @author Guimu
     * @date 2019-06-17
     */
    private int[] topK(int[] arr, int k) {
        int[] result = Arrays.copyOfRange(arr, 0, k);
        buildMaxHeap(result, k);
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > result[0]) {
                result[0] = arr[i];
                heapApiFy(result, 0, k);
            }
        }
        return result;
    }

    @Test
    public void topKtest() {
        int[] arr = {2, 8, 23, 18, 9, 6, 0, 1, 16, 17};
        arr = topK(arr, 3);
        heapSort(arr);
        insert(arr, 21, 0);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test() {
        int[] arr = new int[10];
        int size = 0;
        buildMaxHeap(arr, size);
        int[] newVals = {7, 4, 8, 2, 9, 0};
        for (int val : newVals) {
            insert(arr, val, size++);
        }
    }

    /**
     * 将新值添加在arr堆中, index为新增值的索引, 不能超过索引范围
     *
     * @param arr :
     * @author Guimu
     * @date 2019-04-08 12:38
     */
    private void insert(int[] arr, int newVal, int index) {
        arr[index] = newVal;
        int parIndex = (index - 1) >> 1;
        while (parIndex >= 0 && arr[index] > arr[parIndex]) {
            swap(arr, index, parIndex);
            index = parIndex;
            parIndex = (index - 1) >> 1;
        }
    }


    private void swap(int[] arr, int index, int parIndex) {
        if (parIndex != index) {
            arr[index] = arr[parIndex] - arr[index];
            arr[parIndex] = arr[parIndex] - arr[index];
            arr[index] = arr[parIndex] + arr[index];
        }
    }
}


