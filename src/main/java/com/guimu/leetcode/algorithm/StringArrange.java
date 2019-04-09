package com.guimu.leetcode.algorithm;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

/**
 * @Description: 字符串的全排列
 * @Author: Guimu
 * @Create: 2019/04/05 09:59:38
 **/

public class StringArrange {

    @Test
    public void teg() {
        // comBin("aabc".toCharArray(), true, 3).stream().forEach(el -> perm(el.toCharArray(), 0));
        char[] chs = "abc".toCharArray();
//        for (int i = 1, len = chs.length; i <= len; i++) {
//            combine(chs, len, i, new LinkedList<>());
//        }
        combine(chs, 3, 1, new LinkedList<>());
    }

    public void combine(char[] chs, int begin, int number, List<String> list) {
        if (number == 0) {
            System.out.println(list);
            return;
        }
        if (begin == 0) {
            return;
        }
        list.add(String.valueOf(chs[chs.length - begin]));
        combine(chs, begin - 1, number - 1, list);
        list.remove(list.size() - 1);
        combine(chs, begin - 1, number, list);
    }


    private Collection<String> comBin(char[] chars, boolean flag, int m) {
        return Stream.iterate(0, n -> n + 1)
            .limit(1 << chars.length).filter(el -> {
                if (!flag) {
                    return true;
                }
                int count = 0;
                while (el != 0) {
                    count++;
                    el &= el - 1;
                }
                return count == m;
            })
            .map(el -> {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0, len = chars.length; i < len; i++) {
                    if ((el & (1 << i)) > 0) {
                        stringBuilder.append(chars[i]);
                    }
                }
                return stringBuilder.toString();
            }).filter(el -> !StringUtils.isEmpty(el)).collect(Collectors.toSet());
    }


    /**
     * @Author: Guimu 求字符数组的全排列
     * @Description:
     * @Param:
     * @Return:
     * @Date: 2019-04-05 14:38
     */
    private void perm(char[] chs, int start) {
        if (start == chs.length - 1) {
            System.out.println(Arrays.toString(chs));
        } else {
            for (int i = start, len = chs.length; i < len; i++) {
                if (i != start && chs[i] == chs[start]) {
                    continue;
                }
                swap(chs, i, start);
                perm(chs, start + 1);
                swap(chs, i, start);
            }
        }
    }

    /**
     * @Author: Guimu
     * @Description: 交换两个不同位置的数据
     * @Param:
     * @Return:
     * @Date: 2019-04-05 18:57
     */
    private void swap(char[] chars, int i, int j) {
        if (i != j) {
            chars[i] = (char) ((int) chars[j] - (int) chars[i]);
            chars[j] = (char) ((int) chars[j] - (int) chars[i]);
            chars[i] = (char) ((int) chars[j] + (int) chars[i]);
        }
    }
}
