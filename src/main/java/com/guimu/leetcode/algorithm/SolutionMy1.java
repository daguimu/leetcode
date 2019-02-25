package com.guimu.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: 电线接口问题, 输入N条电线的接口型号, 判断能否接成一条线(接口想好相同才能接)
 * @Author: Guimu
 * @Create: 2019/02/25 10:19:01
 **/

public class SolutionMy1 {

    public static void main(String[] args) {

        List<String> lines = new ArrayList<>(Arrays.asList("ab", "bc", "cd"));
        List<String> inList = new ArrayList<>();
        List<String> outList = new ArrayList<>();
        crerate(lines, inList, outList);
        Iterator<String> iterator = inList.iterator();
        List<Integer> removeList = new ArrayList<>();
        while (iterator.hasNext()) {
            String inTemp = iterator.next();
            if (outList.contains(inTemp)) {
                iterator.remove();
                removeList.add(outList.indexOf(inTemp));
            }
        }
        System.out.println(resu(outList.size() + inList.size() - removeList.size()));
    }

    private static void crerate(List<String> lines, List<String> inList, List<String> outLIst) {
        lines.forEach(el -> {
            inList.add(el.charAt(0) + "");
            outLIst.add(el.charAt(1) + "");
        });
    }

    private static boolean resu(int num) {
        return num == 0 || num == 2;
    }
}
