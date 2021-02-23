/*
 * Copyright (c) 2021 maoyan.com
 * All rights reserved.
 *
 */
package com.guimu.leetcode.controller;

import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Guimu
 * @date 2021/01/30
 */
@RestController
public class TestController {

    @GetMapping(value = "/push",produces = "text/event-stream")
    public String push() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Date().toString();
    }
}