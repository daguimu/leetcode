/*
 * Copyright (c) 2021 maoyan.com
 * All rights reserved.
 *
 */
package com.guimu.leetcode.engineering;

/**
 * @author Guimu
 * @date 2021/02/27
 */
public class RunningDone {

    public static void main(String[] args) {
        warning();
    }


    /**
     * 发出警报声
     *
     * @author Guimu
     * @date 2021/2/27
     */
    public static void warning() {
        String filePath = "/Users/guimu/Downloads/warning.mp3";


        Monitoring.loopPlay(filePath);
    }
}

