/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.guimu.leetcode.algorithm;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author: Guimu
 * @created: 2019/06/17
 */
public class KthLargest {

    private Integer k;
    private int[] arr;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.arr = nums;
        init();
    }

    private void init() {
        this.arr = this.topK(this.arr, k);
        buildMaxHeap(this.arr, this.arr.length);
    }

    public int add(int val) {
        if (val > this.arr[0]) {
            this.arr[0] = val;
            heapApiFy(arr, 0, this.arr.length);
        }
        return this.arr[0];
    }

    private int[] topK(int[] arr, int k) {
        boolean flag = arr.length < k;
        int len = arr.length;
        int[] result = Arrays.copyOfRange(arr, 0, k);
        if (flag) {
            Arrays.fill(result, len, k, Integer.MIN_VALUE);
        }
        buildMaxHeap(result, k);
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > result[0]) {
                result[0] = arr[i];
                heapApiFy(result, 0, k);
            }
        }
        return result;
    }


    private void swap(int[] arr, int index, int parIndex) {
        if (parIndex != index) {
            arr[index] = arr[parIndex] - arr[index];
            arr[parIndex] = arr[parIndex] - arr[index];
            arr[index] = arr[parIndex] + arr[index];
        }
    }


    private void buildMaxHeap(int[] arr, int size) {
        Stream.iterate((size >> 1) - 1, x -> x - 1)
            .limit((size >> 1))
            .forEach(el -> heapApiFy(arr, el, size));
    }

    /**
     * 构建最大堆 使用 > 构建最小堆 使用 <
     *
     * @author: Guimu
     * @created: 2019-06-17
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

    public static void main(String[] args) {
        int k = 3;
        int[] arr = new int[]{1, 1};
        KthLargest kthLargest = new KthLargest(k, arr);
        System.out.println(kthLargest.add(1));
        System.out.println(kthLargest.add(1));
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(4));
        System.out.println(kthLargest.add(4));
        System.out.println(kthLargest.add(4));
    }
}

/**
 * Your KthLargest object will be instantiated and called as such: KthLargest obj = new
 * KthLargest(k, nums); int param_1 = obj.add(val);
 */