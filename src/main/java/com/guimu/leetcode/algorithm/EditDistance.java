/*
 * Copyright (c) 2021 maoyan.com
 * All rights reserved.
 *
 */
package com.guimu.leetcode.algorithm;

import org.springframework.util.StringUtils;

/**
 * @author Guimu
 * @date 2021/02/10
 */
public class EditDistance {

    public static void main(String[] args) {
        //""
        //""
        String s2 = "pneumonoultramicroscopicsilicovolcanoconiosis";
        String s1 = "ultramicroscopically";
        System.out.println(getMinDistance(s1, s2));
    }


    public static int getMinDistance(String s1, String s2) {
        s1 = s1 == null ? "" : s1;
        s2 = s2 == null ? "" : s2;

        if (StringUtils.isEmpty(s1) || StringUtils.isEmpty(s2)) {
            return StringUtils.isEmpty(s1) ? s2.length() : s1.length();
        }
        int len1 = s1.length();
        int len2 = s2.length();

        int[][] d = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            d[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            d[0][j] = j;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1]);
                } else {
                    d[i][j] = Math.min(Math.min(d[i - 1][j], d[i][j - 1]), d[i - 1][j - 1]) + 1;
                }
            }
        }
        return d[len1][len2];
    }
}