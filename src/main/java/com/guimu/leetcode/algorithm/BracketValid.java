/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.guimu.leetcode.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.junit.jupiter.api.Test;

/**
 * @author: Guimu
 * @created: 2019/06/17
 */
public class BracketValid {

    public boolean valid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(')', '(');
        map.put(']', '[');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char el = s.charAt(i);
            // 如果是右括号
            if (map.keySet().contains(el)) {
                if (!stack.isEmpty() && map.get(el).equals(stack.peek())) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(el);
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void ui() {
        System.out.println(valid("()"));
    }
}