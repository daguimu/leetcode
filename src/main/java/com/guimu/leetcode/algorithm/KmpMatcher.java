package com.guimu.leetcode.algorithm;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * @Description: kmp
 * @Author: Guimu
 * @Create: 2019/04/07 11:34:36
 **/

public class KmpMatcher {

    @Test
    public void test() {
        System.out.println(Arrays.toString(createNextTable("ababa".toCharArray())));
        kmpMatch("abbbbbababbasdababab", "ababa");
    }

    /**
     * @Author: Guimu
     * @Description: 生成字符串的最大前后缀匹配表
     * @Param:
     * @Return:
     * @Date: 2019-04-07 11:35
     */
    private int[] createNextTable(char... chs) {
        int len = chs.length;
        int[] arr = new int[len];
        arr[0] = 0;

        for (int k = 0, i = 1; i < len; i++) {
            while (k > 0 && chs[i] != chs[k]) {
                k = arr[k - 1];
            }
            if (chs[i] == chs[k]) {
                k++;
            }
            arr[i] = k;
        }
        return arr;
    }

    private void kmpMatch(String fStr, String subStr) {

        int[] next = createNextTable(subStr.toCharArray());
        int q = 0;
        for (int i = 0, len = fStr.length(); i < len; i++) {
            while (q > 0 && fStr.charAt(i) != subStr.charAt(q)) {
                q = next[q - 1];
            }
            if (fStr.charAt(i) == subStr.charAt(q)) {
                q++;
            }
            if (q == subStr.length()) {
                System.out.println(i - subStr.length() + 1);
                q = 0;// 将已匹配的数置为0,已匹配所有子串
            }
        }
    }
}
