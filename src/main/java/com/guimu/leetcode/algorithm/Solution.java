package com.guimu.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 算法类型的问题
 * @Author: Guimu
 * @Create: 2019/02/23 12:43:28
 **/

class Solution {

    /**
     * @Author: Guimu
     * @Description: 17. Letter Combinations of a Phone Number
     * @Param: [telNoStr]
     * @Return: java.util.List<java.lang.String>
     * @Date: 2019-02-23 15:38
     */
    public List<String> letterCombinations(String telNoStr) {
        if (telNoStr == null || telNoStr.length() == 0) {
            return new ArrayList();
        }
        char[] telStr = telNoStr.toCharArray();
        List<String> values = new ArrayList<>();
        for (char w : telStr) {
            values.add(this.telKeyToStr((int) w - 48));
        }
        StringBuilder stringBuilder = null;
        String[] temp = convent(values.get(0), 1);
        for (int i = 1; i < values.size(); i++) {
            stringBuilder = new StringBuilder();
            fun1(temp, values.get(i), stringBuilder);
            temp = convent(stringBuilder.toString(), i + 1);
        }
        String[] arr = convent(
            stringBuilder != null ? stringBuilder.toString() : telKeyToStr(telNoStr.charAt(0) - 48),
            telNoStr.length());
        return new ArrayList(Arrays.asList(arr));
    }

    private String[] convent(String str, int teep) {
        List<String> resu = new ArrayList<>();
        while (str.length() > 0) {
            resu.add(str.substring(0, teep));
            str = str.substring(teep);
        }
        return resu.toArray(new String[0]);
    }

    private String telKeyToStr(int telKey) {
        StringBuilder str = new StringBuilder();
        int baseInt = (telKey - 1) * 3;
        baseInt = telKey > 7 ? baseInt + 1 : baseInt;
        for (int i = 94; i < 97; i++) {
            str.append((char) (baseInt + i));
        }
        if (telKey == 7 || telKey == 9) {
            str.append((char) (baseInt + 97));
        }
        return str.toString();
    }

    private void fun1(String[] str1, String str2, StringBuilder builder) {
        for (int i = 0; i < str1.length; i++) {
            for (int j = 0; j < str2.length(); j++) {
                builder.append(str1[i]);
                builder.append(str2.charAt(j));
            }
        }
    }
}