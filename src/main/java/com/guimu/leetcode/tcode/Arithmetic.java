/*
 * Copyright (c) 2021 maoyan.com
 * All rights reserved.
 *
 */
package com.guimu.leetcode.tcode;

import com.googlecode.aviator.AviatorEvaluator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

/**
 * @author Guimu
 * @date 2021/01/30
 */
public class Arithmetic {

    private static final String MUL = "*";
    private static final String ADD = "+";
    private static final String MINS = "-";
    private static final String DIV = "/";
//    private static final String DIV = "÷";


    private static final Random ROM = new Random();

    private static final String[] OPS = {MINS, ADD, MUL};

    public static void main(String[] args) {
        List<Metic> meticList = generator(200, true, OPS);
//        meticList.forEach(el -> System.out.println(el.getDesc() + " = " + el.getVal()));
        PdfReport.pdfGenter(meticList);
    }


    private static List<Metic> generator(int size, boolean floatFlag, String... ops) {
        List<Metic> meticList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            metic(2 + (floatFlag ? ROM.nextInt(2) : 0), meticList, ops);
        }
        return meticList;
    }


    private static void metic(int oper, List<Metic> meticList, String[] ops) {
        metic(oper, ops, meticList, 20, 100);
    }

    private static Long metic(int oper, String[] ops, List<Metic> meticList, int min, int max) {
        StringBuilder builder = new StringBuilder();
        Stack<Integer> stNum = new Stack<>();
        Stack<String> stOps = new Stack<>();
        for (int i = 0; i < oper; i++) {
            int op1;
            if (!stNum.isEmpty() && stOps.peek().equals(MINS)) {
                op1 = getTomNumber((int) Math.log10(stNum.peek()) + 1, stNum.peek(), false);
            } else if (!stOps.isEmpty() && stOps.peek().equals(MUL)) {
                op1 = getTomNumber(ROM.nextInt(2) + 1, 1, true);
            } else {
                op1 = getTomNumber(ROM.nextInt(2) + 1);
            }
            builder.append(op1);
            stNum.push(op1);
            if (i < oper - 1) {
                String curOps = getOps(ops);
                builder.append(curOps);
                stOps.push(curOps);
            }
        }
        String evalStr = builder.toString();
        Long result = (Long) AviatorEvaluator.execute(evalStr);
        if (result >= min && result < max) {
            Metic metic = new Metic();
            metic.setVal(result);
            metic.setDesc(evalStr.replaceAll("[*]", " x ").replaceAll("/", " ÷ ")
                .replaceAll("\\+", "  \\+  ").replaceAll("-", " \\-  "));
            meticList.add(metic);
        } else {
            result = metic(oper, ops, meticList, min, max);
        }
        return result;
    }

    private static int getTomNumber(int len) {
        int min = (int) Math.pow(10, len - 1);
        int max = (int) Math.pow(10, len);
        int resu = min + (int) (Math.random() * (max - min + 1));
        return resu == 1 ? getTomNumber(len) : resu;
    }

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getTomNumber(1));
        }

    }

    private static int getTomNumber(int len, Integer source, boolean excludeFlag) {
        int val = getTomNumber(len);
        while ((excludeFlag && val == source) || (!excludeFlag && val > source)) {
            val = getTomNumber(len);
        }
        return val;
    }

    private static String getOps(String... ops) {
        return ops[ROM.nextInt(ops.length)];
    }

}

@Getter
@Setter
class Metic {

    private String desc;
    private Long val;

}